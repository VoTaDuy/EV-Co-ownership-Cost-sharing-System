package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.GroupFundRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.GroupFundServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupFundService implements GroupFundServicesImp {

    @Autowired
    private GroupFundRepository groupFundRepository;


    @Override
    public GroupFund createFund(GroupFund fund) {
        if (fund.getGroupId() == null) {
            throw new RuntimeException("Quỹ phải thuộc một nhóm hợp lệ!");
        }

        fund.setCreated_at(LocalDateTime.now());
        fund.setUpdated_at(LocalDateTime.now());

        return groupFundRepository.save(fund);
    }

    @Override
    public List<GroupFund> getAllFunds() {
        return groupFundRepository.findAll();
    }

    @Override
    public GroupFund getFundById(int id) {
        return groupFundRepository.findByFund_id(id);
    }

    @Override
    public List<GroupFund> getFundsByGroupId(String groupId) {
        return List.of();
    }

    @Override
    public GroupFund updateFund(int id, GroupFund updatedFund) {
        GroupFund fund = groupFundRepository.findByFund_id(id);
        if (fund == null) {
            throw new RuntimeException("Không tìm thấy quỹ với ID = " + id);
        }

        fund.setFund_name(updatedFund.getFund_name());
        fund.setBalance(updatedFund.getBalance());
        fund.setUpdated_at(LocalDateTime.now());

        return groupFundRepository.save(fund);
    }

    @Override
    public void deleteFund(int id) {
        GroupFund fund = groupFundRepository.findByFund_id(id);
        if (fund == null) {
            throw new RuntimeException("Không tìm thấy quỹ với ID = " + id);
        }
        groupFundRepository.delete(fund);
    }
}