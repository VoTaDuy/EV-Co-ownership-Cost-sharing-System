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
    public List<PaymentDTO> getByGroup(String groupId) {
        return paymentRepo.findByGroupId(groupId).stream()
                .map(PaymentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getByUser(String userId) {
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
    public PaymentDTO createPayment(Map<String, Object> request, String userId) {
        Payment payment = new Payment();
        payment.setGroupId((String) request.get("groupId"));
        payment.setUserId(userId);
        payment.setAmount(new BigDecimal(request.get("amount").toString()));
        payment.setGateway((String) request.get("gateway"));
        payment.setGatewayOrderId((String) request.get("gatewayOrderId"));
        payment.setPaymentDate(LocalDateTime.now());

        if (request.get("costId") != null) {
            VehicleCost cost = costRepo.findById((Integer) request.get("costId"))
                    .orElseThrow(() -> new NotFoundException("Cost not found"));
            payment.setCost(cost);
        }

        if (request.get("fundId") != null) {
            GroupFund fund = fundRepo.findById((Integer) request.get("fundId"))
                    .orElseThrow(() -> new NotFoundException("Fund not found"));
            payment.setFund(fund);
        }

        return PaymentDTO.fromEntity(paymentRepo.save(payment));
    }

    @Override
    public PaymentDTO handleGatewayCallback(String gatewayOrderId, String status, String response) {
        Payment payment = paymentRepo.findByGatewayOrderId(gatewayOrderId)
                .orElseThrow(() -> new NotFoundException("Payment not found for gatewayOrderId: " + gatewayOrderId));

        payment.setGatewayResponse(response);

        switch (status.toLowerCase()) {
            case "success", "completed" -> payment.setStatus(Payment.PaymentStatus.completed);
            case "failed" -> payment.setStatus(Payment.PaymentStatus.failed);
            case "refunded" -> payment.setStatus(Payment.PaymentStatus.refunded);
            default -> payment.setStatus(Payment.PaymentStatus.pending);
        }

        return PaymentDTO.fromEntity(paymentRepo.save(payment));
    }
}
