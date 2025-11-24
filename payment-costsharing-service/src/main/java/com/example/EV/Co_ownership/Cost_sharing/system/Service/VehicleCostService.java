package com.example.EV.Co_ownership.Cost_sharing.system.Service;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.CreateCostRequest;
import com.example.EV.Co_ownership.Cost_sharing.system.DTO.VehicleCostDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.Entity.FundTransaction;
import com.example.EV.Co_ownership.Cost_sharing.system.Entity.GroupFund;
import com.example.EV.Co_ownership.Cost_sharing.system.Entity.VehicleCost;
import com.example.EV.Co_ownership.Cost_sharing.system.Enum.TransactionType;
import com.example.EV.Co_ownership.Cost_sharing.system.Enum.VehicleCostStatus;
import com.example.EV.Co_ownership.Cost_sharing.system.Exception.NotFoundException;
import com.example.EV.Co_ownership.Cost_sharing.system.Repository.FundTransactionRepository;
import com.example.EV.Co_ownership.Cost_sharing.system.Repository.GroupFundRepository;
import com.example.EV.Co_ownership.Cost_sharing.system.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.VehicleCostServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleCostService implements VehicleCostServiceImp {

    private final VehicleCostRepository costRepo;
    private final GroupFundRepository fundRepo;
    private final FundTransactionRepository txRepo;

    @Override
    public List<VehicleCostDTO> getAllByGroup(int groupId) {
        return costRepo.findByGroupId(groupId).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<VehicleCostDTO> getAllByFund(Integer fundId) {
        return costRepo.findByFund_FundId(fundId).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public VehicleCostDTO create(CreateCostRequest request, int userId) {
        VehicleCost cost = new VehicleCost();
        cost.setGroupId(request.groupId());
        cost.setVehicleId(request.vehicleId());
        cost.setCostName(request.costName());
        cost.setAmount(request.amount());

        if (request.fundId() != null) {
            GroupFund fund = fundRepo.findById(request.fundId())
                    .orElseThrow(() -> new NotFoundException("Quỹ không tồn tại: " + request.fundId()));
            cost.setFund(fund);
        }

        cost = costRepo.save(cost);

        // Ghi log giao dịch nếu dùng quỹ
        if (request.fundId() != null && cost.getStatus() == VehicleCostStatus.paid) {
            logFundTransfer(cost, userId);
        }

        return toDTO(cost);
    }

    @Override
    public VehicleCostDTO getById(Integer costId) {
        return costRepo.findById(costId)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Chi phí không tồn tại: " + costId));
    }

    @Override
    public VehicleCostDTO updateStatus(Integer costId, String status, int userId) {
        VehicleCost cost = costRepo.findById(costId)
                .orElseThrow(() -> new NotFoundException("Chi phí không tồn tại: " + costId));

        VehicleCostStatus newStatus = VehicleCostStatus.valueOf(status.toLowerCase());
        cost.setStatus(newStatus);

        // Nếu chuyển sang "paid" và có dùng quỹ → trừ tiền
        if (newStatus == VehicleCostStatus.paid && cost.getFund() != null) {
            GroupFund fund = cost.getFund();
            if (fund.getBalance().compareTo(cost.getAmount()) < 0) {
                throw new IllegalStateException("Số dư quỹ không đủ để thanh toán chi phí này.");
            }
            fund.setBalance(fund.getBalance().subtract(cost.getAmount()));
            fundRepo.save(fund);

            logFundTransfer(cost, userId);
        }

        cost = costRepo.save(cost);
        return toDTO(cost);
    }

    @Override
    public void delete(Integer costId) {
        VehicleCost cost = costRepo.findById(costId)
                .orElseThrow(() -> new NotFoundException("Chi phí không tồn tại: " + costId));
        costRepo.delete(cost);
    }

    private void logFundTransfer(VehicleCost cost, int userId) {
        FundTransaction tx = new FundTransaction();
        tx.setFund(cost.getFund());
        tx.setCost(cost);
        tx.setTransactionType(TransactionType.transfer);
        tx.setAmount(cost.getAmount());
        tx.setDescription("Thanh toán chi phí xe: " + cost.getCostName());
        tx.setPerformedBy(userId);
        tx.setStatus("success");
        txRepo.save(tx);
    }

    private VehicleCostDTO toDTO(VehicleCost cost) {
        return new VehicleCostDTO(
                cost.getCostId(),
                cost.getGroupId(),
                cost.getFund() != null ? cost.getFund().getFundId() : null,
                cost.getVehicleId(),
                cost.getCostName(),
                cost.getAmount(),
                cost.getStatus().name(),
                cost.getCreatedAt(),
                cost.getUpdatedAt()
        );
    }
}