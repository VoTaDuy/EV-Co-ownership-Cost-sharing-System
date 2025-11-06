package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.GroupFundDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupTable;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.GroupFundRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.GroupTableRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.UserGroupsRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.GroupFundServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupFundService implements GroupFundServicesImp {

    @Autowired
    private GroupFundRepository groupFundRepository;

    @Autowired
    private GroupTableRepository groupTableRepository;

    @Autowired
    private UserGroupsRepository userGroupsRepository;

    @Override
    public GroupFund createFund(GroupFund fund) {
        if (fund.getGroup_id() == null || fund.getGroup_id().getGroup_id() == 0) {
            throw new RuntimeException("Quỹ phải thuộc một nhóm hợp lệ!");
        }

        // Lấy group thực tế từ DB
        GroupTable group = groupTableRepository.findById(fund.getGroup_id().getGroup_id())
                .orElseThrow(() -> new RuntimeException("Nhóm không tồn tại!"));

        // Kiểm tra người tạo quỹ có thuộc nhóm không
        boolean isMember = userGroupsRepository.checkUserInGroup(group.getGroup_id(), fund.getCreated_by()).isPresent();


        if (!isMember) {
            throw new RuntimeException("Người tạo quỹ phải là thành viên của nhóm!");
        }

        fund.setGroup_id(group);
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
    public List<GroupFund> getFundsByGroupId(int groupId) {
        return groupFundRepository.findByGroupId(groupId);
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