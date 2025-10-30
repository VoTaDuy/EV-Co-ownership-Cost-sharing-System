package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.GroupFundDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;

import java.util.List;

public interface GroupFundServicesImp {
    List<GroupFund> getAllFunds();

    GroupFund getFundById(int id);

    List<GroupFund> getFundsByGroupId(int groupId);

    GroupFund createFund(GroupFund fund);

    GroupFund updateFund(int id, GroupFund fund);

    void deleteFund(int id);
}