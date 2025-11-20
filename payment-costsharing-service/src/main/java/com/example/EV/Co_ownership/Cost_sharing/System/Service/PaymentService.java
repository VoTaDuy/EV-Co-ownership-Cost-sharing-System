package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PaymentDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Payment;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;
import com.example.EV.Co_ownership.Cost_sharing.System.Exception.NotFoundException;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.GroupFundRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PaymentRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PaymentServiceImp;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService implements PaymentServiceImp {

    private final PaymentRepository paymentRepo;
    private final VehicleCostRepository costRepo;
    private final GroupFundRepository fundRepo;

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepo.findAll().stream()
                .map(PaymentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getByGroup(int groupId) {
        return paymentRepo.findByGroupId(groupId).stream()
                .map(PaymentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getByUser(int userId) {
        return paymentRepo.findByUserId(userId).stream()
                .map(PaymentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getByCost(Integer costId) {
        return paymentRepo.findByCost_CostId(costId).stream()
                .map(PaymentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getByFund(Integer fundId) {
        return paymentRepo.findByFund_FundId(fundId).stream()
                .map(PaymentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO createPayment(Map<String, Object> request, int userId) {
        Payment payment = new Payment();

        // === groupId ===
        Object groupIdObj = request.get("groupId");
        if (groupIdObj == null) throw new IllegalArgumentException("groupId is required");
        int groupId = convertToInt(groupIdObj, "groupId");

        // === amount ===
        Object amountObj = request.get("amount");
        if (amountObj == null) throw new IllegalArgumentException("amount is required");
        BigDecimal amount = new BigDecimal(amountObj.toString());

        // === gatewayOrderId (tự tạo nếu null) ===
        String gatewayOrderId = (String) request.get("gatewayOrderId");
        if (gatewayOrderId == null || gatewayOrderId.isBlank()) {
            gatewayOrderId = "FAKE_" + System.currentTimeMillis();
        }
        if (paymentRepo.findByGatewayOrderId(gatewayOrderId).isPresent()) {
            throw new IllegalArgumentException("gatewayOrderId already exists: " + gatewayOrderId);
        }

        // === gateway ===
        String gateway = (String) request.get("gateway");
        if (gateway == null || gateway.isBlank()) gateway = "fake";

        // === Thiết lập ===
        payment.setGroupId(groupId);
        payment.setUserId(userId);
        payment.setAmount(amount);
        payment.setGateway(gateway);
        payment.setGatewayOrderId(gatewayOrderId);
        payment.setStatus(Payment.PaymentStatus.pending);
        payment.setCreatedAt(LocalDateTime.now());

        // === costId / fundId ===
        if (request.containsKey("costId") && request.get("costId") != null) {
            int costId = convertToInt(request.get("costId"), "costId");
            VehicleCost cost = costRepo.findById(costId)
                    .orElseThrow(() -> new NotFoundException("Cost not found: " + costId));
            payment.setCost(cost);
        }

        if (request.containsKey("fundId") && request.get("fundId") != null) {
            int fundId = convertToInt(request.get("fundId"), "fundId");
            GroupFund fund = fundRepo.findById(fundId)
                    .orElseThrow(() -> new NotFoundException("Fund not found: " + fundId));
            payment.setFund(fund);
        }

        return PaymentDTO.fromEntity(paymentRepo.save(payment));
    }

    // === Helper: Chuyển Object → int an toàn ===
    private int convertToInt(Object obj, String fieldName) {
        if (obj instanceof Integer) return (Integer) obj;
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(fieldName + " must be a valid number");
            }
        }
        throw new IllegalArgumentException(fieldName + " must be integer or string number");
    }

    @Override
    public PaymentDTO handleGatewayCallback(String gatewayOrderId, String status, String response) {
        Payment payment = paymentRepo.findByGatewayOrderId(gatewayOrderId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thanh toán: " + gatewayOrderId));

        payment.setGatewayResponse(response);

        Payment.PaymentStatus oldStatus = payment.getStatus();

        switch (status.toLowerCase()) {
            case "success", "completed" -> payment.setStatus(Payment.PaymentStatus.completed);
            case "failed" -> payment.setStatus(Payment.PaymentStatus.failed);
            case "refunded" -> payment.setStatus(Payment.PaymentStatus.refunded);
            default -> payment.setStatus(Payment.PaymentStatus.pending);
        }

        // === CẬP NHẬT BALANCE KHI CHUYỂN SANG COMPLETED ===
        if (payment.getStatus() == Payment.PaymentStatus.completed
                && oldStatus != Payment.PaymentStatus.completed
                && payment.getFund() != null) {

            GroupFund fund = payment.getFund();
            fund.setBalance(fund.getBalance().add(payment.getAmount()));
            fundRepo.save(fund);
        }

        payment.setPaymentDate(LocalDateTime.now());
        return PaymentDTO.fromEntity(paymentRepo.save(payment));
    }

    @Override
    public PaymentDTO handleFakePaymentSuccess(String gatewayOrderId) {
        // LẤY PAYMENT + FUND (eager load)
        Payment payment = paymentRepo.findByGatewayOrderId(gatewayOrderId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thanh toán: " + gatewayOrderId));

        // BẮT BUỘC LOAD FUND (nếu dùng Lazy)
        if (payment.getFund() != null) {
            Hibernate.initialize(payment.getFund());
        }

        Payment.PaymentStatus oldStatus = payment.getStatus();
        payment.setStatus(Payment.PaymentStatus.completed);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setGatewayResponse("{\"fake\": true, \"time\": \"" + LocalDateTime.now() + "\"}");

        // === CẬP NHẬT BALANCE ===
        if (oldStatus != Payment.PaymentStatus.completed && payment.getFund() != null) {
            GroupFund fund = payment.getFund();
            BigDecimal newBalance = fund.getBalance().add(payment.getAmount());
            fund.setBalance(newBalance);
            fundRepo.save(fund); // BẮT BUỘC SAVE
        }

        return PaymentDTO.fromEntity(paymentRepo.save(payment));
    }
}
