package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.*;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.GroupMembersRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.GroupUsersRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupUsersRepository groupUsersRepository;

    @Autowired
    private GroupMembersRepository groupMembersRepository;

    @Autowired
    private UserRepository usersRepository;

    public GroupUsers createGroup(GroupUsers group) {
        return groupUsersRepository.save(group);
    }

    public GroupMembers addMember(int userId, int groupId, Float ownershipRatio) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User ID: " + userId));

        GroupUsers group = groupUsersRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Group ID: " + groupId));

        GroupMemberId id = new GroupMemberId(userId, groupId);
        GroupMembers member = new GroupMembers();
        member.setId(id);
        member.setUser(user);
        member.setGroup(group);
        member.setOwnership_ratio(ownershipRatio);

        return groupMembersRepository.save(member);
    }

    public List<GroupMembers> getGroupsByUserId(int userId) {
        return groupMembersRepository.findByUser_UserId(userId);
    }

    public List<GroupMembers> getMembersByGroupId(int groupId) {
        return groupMembersRepository.findByGroup_GroupId(groupId);
    }
}
