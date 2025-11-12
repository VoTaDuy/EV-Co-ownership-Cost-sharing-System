package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CreateFundRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.DepositRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.FundTransactionDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.GroupFundDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.FundTransaction;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Payment;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.TransactionType;
import com.example.EV.Co_ownership.Cost_sharing.System.Exception.NotFoundException;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.FundTransactionRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.GroupFundRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PaymentRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.GroupFundServiceImp;
import com.example.EV.Co_ownership.Cost_sharing.System.Util.PaymentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupFundService implements GroupFundServiceImp {

    private final GroupFundRepository fundRepo;
    private final FundTransactionRepository txRepo;
    private final PaymentRepository paymentRepo;

    // SỬA: LỌC THEO GROUP ID
    @Override
    public List<GroupFundDTO> getAll(String groupId) {
        if (groupId == null || groupId.isBlank()) {
            return fundRepo.findAll().stream()
                    .map(this::toDTO)
                    .toList();
        }
        return fundRepo.findByGroupId(groupId).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public GroupFundDTO create(CreateFundRequest request, String userId) {
        GroupFund fund = new GroupFund();
        fund.setGroupId(request.groupId());
        fund.setFundName(request.fundName());
        fund.setBalance(request.initialBalance() != null ? request.initialBalance() : BigDecimal.ZERO);
        fund.setCreatedBy(userId);
        fund = fundRepo.save(fund);

        if (request.initialBalance() != null && request.initialBalance().compareTo(BigDecimal.ZERO) > 0) {
            logInitialDeposit(fund, request.initialBalance(), userId);
        }

        return toDTO(fund);
    }

    @Override
    public GroupFundDTO getById(Integer fundId) {
        return fundRepo.findById(fundId)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Quỹ không tồn tại: " + fundId));
    }

    @Override
    public String initiateDeposit(Integer fundId, DepositRequest request, String userId) {
        GroupFund fund = fundRepo.findById(fundId)
                .orElseThrow(() -> new NotFoundException("Quỹ không tồn tại"));

        String orderId = "DEPOSIT_" + System.currentTimeMillis() + "_" + fundId;
        String description = "Nạp " + request.amount() + " vào quỹ \"" + fund.getFundName() + "\"";

        Payment payment = new Payment();
        payment.setGroupId(fund.getGroupId());
        payment.setUserId(userId);
        payment.setFund(fund);
        payment.setAmount(request.amount());
        payment.setGateway(request.gateway());
        payment.setGatewayOrderId(orderId);
        payment.setStatus(Payment.PaymentStatus.pending);
        payment = paymentRepo.save(payment);

        FundTransaction tx = new FundTransaction();
        tx.setFund(fund);
        tx.setPayment(payment);
        tx.setTransactionType(TransactionType.deposit);
        tx.setAmount(request.amount());
        tx.setDescription(description);
        tx.setPerformedBy(userId);
        tx.setGatewayOrderId(orderId);
        tx.setStatus("pending");
        txRepo.save(tx);

        return PaymentUtil.createPaymentUrl(orderId, request.amount(), description, request.gateway());
    }

    @Override
    public void handleDepositCallback(String orderId, String status, String transactionId, String response) {
        Payment payment = paymentRepo.findByGatewayOrderId(orderId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thanh toán: " + orderId));

        boolean success = "0".equals(status) || "00".equals(status);

        payment.setGatewayResponse(response);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(success ? Payment.PaymentStatus.completed : Payment.PaymentStatus.failed);
        paymentRepo.save(payment);

        if (success && payment.getFund() != null) {
            GroupFund fund = payment.getFund();
            fund.setBalance(fund.getBalance().add(payment.getAmount()));
            fundRepo.save(fund);

            txRepo.findByGatewayOrderId(orderId).ifPresent(tx -> {
                tx.setStatus("success");
                txRepo.save(tx);
            });
        }
    }

    // XÓA QUỸ – DB TỰ XÓA fund_transactions
    @Override
    public void deleteFund(Integer fundId, String userId) {
        GroupFund fund = fundRepo.findById(fundId)
                .orElseThrow(() -> new NotFoundException("Quỹ không tồn tại: " + fundId));

        if (!fund.getCreatedBy().equals(userId)) {
            throw new RuntimeException("Bạn không có quyền xóa quỹ này");
        }

        fundRepo.delete(fund); // CASCADE tự xóa
    }

    private void logInitialDeposit(GroupFund fund, BigDecimal amount, String userId) {
        FundTransaction tx = new FundTransaction();
        tx.setFund(fund);
        tx.setTransactionType(TransactionType.deposit);
        tx.setAmount(amount);
        tx.setDescription("Số dư ban đầu");
        tx.setPerformedBy(userId);
        tx.setStatus("success");
        txRepo.save(tx);
    }

    private GroupFundDTO toDTO(GroupFund fund) {
        return new GroupFundDTO(
                fund.getFundId(),
                fund.getGroupId(),
                fund.getFundName(),
                fund.getBalance(),
                fund.getCreatedBy(),
                fund.getCreatedAt(),
                fund.getUpdatedAt()
        );
    }

    @Override
    public List<FundTransactionDTO> getTransactions(Integer fundId) {
        return txRepo.findByFund_FundId(fundId).stream()
                .map(this::toTxDTO)
                .toList();
    }

    private FundTransactionDTO toTxDTO(FundTransaction tx) {
        return new FundTransactionDTO(
                tx.getTransactionId(),
                tx.getFund().getFundId(),
                tx.getPayment() != null ? tx.getPayment().getPaymentId() : null,
                tx.getCost() != null ? tx.getCost().getCostId() : null,
                tx.getTransactionType().name(),
                tx.getAmount(),
                tx.getDescription(),
                tx.getPerformedBy(),
                tx.getGatewayOrderId(),
                tx.getStatus(),
                tx.getCreatedAt()
        );
    }
}