package com.example.EV.Co_ownership.Cost_sharing.system.Service;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.GroupMemberDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse.GroupMember;
import com.example.EV.Co_ownership.Cost_sharing.system.RepositoryData.GroupMemberRepository;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.GroupMemberServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupMemberService implements GroupMemberServiceImp {

    @Autowired
    GroupMemberRepository groupMemberRepository;

    @Override
    public List<GroupMemberDTO> getGroupForUserId(int userId) {
        List<GroupMember> groupMemberList = groupMemberRepository.findByUserId(userId);
        List<GroupMemberDTO> groupMemberDTOList = new ArrayList<>();

        for (GroupMember g : groupMemberList){
            GroupMemberDTO groupMemberDTO = new GroupMemberDTO();
            groupMemberDTO.setGroupId(g.getGroupId());
            groupMemberDTOList.add(groupMemberDTO);
        }
        return groupMemberDTOList;
    }
}
