package com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.GroupMemberDTO;

import java.util.List;

public interface GroupMemberServiceImp {
    List<GroupMemberDTO> getGroupForUserId(int userId);
}
