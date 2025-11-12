package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CostShareDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CreateShareRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.SettleShareRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.CostShare;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.FundTransaction;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.CostShareStatus;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.TransactionType;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.VehicleCostStatus;
import com.example.EV.Co_ownership.Cost_sharing.System.Exception.NotFoundException;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.CostShareRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.FundTransactionRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.CostShareServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CostShareService implements CostShareServiceImp {

    private final CostShareRepository shareRepo;
    private final VehicleCostRepository costRepo;
    private final FundTransactionRepository txRepo;

    @Override
    public List<CostShareDTO> getByCost(Integer costId) {
        return shareRepo.findByCost_CostId(costId).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<CostShareDTO> getByUser(String userId) {
        return shareRepo.findByUserId(userId).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<CostShareDTO> createShares(CreateShareRequest request, String userId) {
        VehicleCost cost = costRepo.findById(request.costId())
                .orElseThrow(() -> new NotFoundException("Chi phí không tồn tại: " + request.costId()));

        // Xóa shares cũ nếu có
        shareRepo.findByCost_CostId(request.costId()).forEach(shareRepo::delete);

        // Kiểm tra tổng % = 100
        BigDecimal totalPercent = request.shares().stream()
                .map(CreateShareRequest.ShareDetail::percentage)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalPercent.compareTo(BigDecimal.valueOf(100)) != 0) {
            throw new IllegalArgumentException("Tổng phần trăm phải bằng 100%");
        }

        List<CostShare> shares = request.shares().stream().map(detail -> {
            BigDecimal amountDue = cost.getAmount()
                    .multiply(detail.percentage())
                    .divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

            CostShare share = new CostShare();
            share.setCost(cost);
            share.setUserId(detail.userId());
            share.setSharePercentage(detail.percentage());
            share.setAmountDue(amountDue);
            share.setSettledAmount(BigDecimal.ZERO);
            share.setStatus(CostShareStatus.unpaid);
            return shareRepo.save(share);
        }).toList();

        if (cost.getStatus() == VehicleCostStatus.paid && cost.getFund() != null) {
            shares.forEach(share -> settleFromFund(share, cost.getFund(), userId));
        }

        return shares.stream().map(this::toDTO).toList();
    }

    @Override
    public CostShareDTO settleShare(Integer shareId, SettleShareRequest request, String userId) {
        CostShare share = shareRepo.findById(shareId)
                .orElseThrow(() -> new NotFoundException("Phần chia không tồn tại: " + shareId));

        if (!share.getUserId().equals(userId)) {
            throw new SecurityException("Không được thanh toán phần của người khác");
        }

        BigDecimal remaining = share.getAmountDue().subtract(share.getSettledAmount());
        BigDecimal settleAmount = request.amount().min(remaining);

        share.setSettledAmount(share.getSettledAmount().add(settleAmount));
        if (share.getSettledAmount().compareTo(share.getAmountDue()) >= 0) {
            share.setStatus(CostShareStatus.paid);
        } else if (share.getSettledAmount().compareTo(BigDecimal.ZERO) > 0) {
            share.setStatus(CostShareStatus.partial);
        }

        share = shareRepo.save(share);

        // Ghi log nếu thanh toán đầy đủ
        if (share.getStatus() == CostShareStatus.paid && share.getCost().getFund() != null) {
            logFundTransfer(share, userId);
        }

        return toDTO(share);
    }

    @Override
    public void deleteByCost(Integer costId) {
        shareRepo.findByCost_CostId(costId).forEach(shareRepo::delete);
    }

    private void settleFromFund(CostShare share, GroupFund fund, String userId) {
        if (fund.getBalance().compareTo(share.getAmountDue()) >= 0) {
            fund.setBalance(fund.getBalance().subtract(share.getAmountDue()));
            share.setSettledAmount(share.getAmountDue());
            share.setStatus(CostShareStatus.paid);
            shareRepo.save(share);
            logFundTransfer(share, userId);
        }
    }

    private void logFundTransfer(CostShare share, String userId) {
        FundTransaction tx = new FundTransaction();
        tx.setFund(share.getCost().getFund());
        tx.setCost(share.getCost());
        tx.setTransactionType(TransactionType.transfer);
        tx.setAmount(share.getAmountDue());
        tx.setDescription("Thanh toán phần chia: " + share.getUserId());
        tx.setPerformedBy(userId);
        tx.setStatus("success");
        txRepo.save(tx);
    }

    private CostShareDTO toDTO(CostShare share) {
        return new CostShareDTO(
                share.getShareId(),
                share.getCost().getCostId(),
                share.getUserId(),
                share.getSharePercentage(),
                share.getAmountDue(),
                share.getSettledAmount(),
                share.getStatus().name(),
                share.getCreatedAt()
        );
    }
}
