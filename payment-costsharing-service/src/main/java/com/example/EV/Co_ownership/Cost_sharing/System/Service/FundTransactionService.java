package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.FundTransactionDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.FundTransaction;
import com.example.EV.Co_ownership.Cost_sharing.System.Exception.NotFoundException;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.FundTransactionRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.FundTransactionRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.FundTransactionsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FundTransactionService implements FundTransactionsServiceImp {

    private final FundTransactionRepository txRepo;

    @Override
    public List<FundTransactionDTO> getByFund(Integer fundId) {
        return txRepo.findByFund_FundId(fundId).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<FundTransactionDTO> getByCost(Integer costId) {
        return txRepo.findByCost_CostId(costId).stream()
                .map(this::toDTO)
                .toList();
    }


    @Override
    public FundTransactionDTO getById(Integer transactionId) {
        return txRepo.findById(transactionId)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Giao dịch không tồn tại: " + transactionId));
    }

    // FundTransactionService.java (implementation)
    @Override
    public List<FundTransactionDTO> getAll() {
        return txRepo.findAll().stream()
                .map(this::toDTO)
                .sorted((a, b) -> b.createdAt().compareTo(a.createdAt()))
                .toList();
    }

    private FundTransactionDTO toDTO(FundTransaction tx) {
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

