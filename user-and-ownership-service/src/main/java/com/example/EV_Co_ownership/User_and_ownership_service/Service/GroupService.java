package com.example.EV_Co_ownership.User_and_ownership_service.Service;

// THÊM CÁC IMPORT NÀY
import com.example.EV_Co_ownership.User_and_ownership_service.Embeddable.GroupMemberId;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.GroupMembers;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Groups;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.GroupMembersRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.GroupUsersRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class GroupService {

    @Autowired
    private GroupUsersRepository groupUsersRepository;

    @Autowired
    private GroupMembersRepository groupMembersRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Groups createGroup(String groupName, String description) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        Users creator = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng đã xác thực."));

        Groups newGroup = new Groups();
        newGroup.setGroupName(groupName);
        newGroup.setDescription(description);
        newGroup.setCreatedDate(LocalDateTime.now());

        Groups savedGroup = groupUsersRepository.save(newGroup);

        GroupMembers firstMember = new GroupMembers(
                creator,
                savedGroup,
                new BigDecimal("100.0"),
                LocalDateTime.now()
        );

        groupMembersRepository.save(firstMember);

        return savedGroup;
    }

    @Transactional
    public GroupMembers addMemberToGroup(int groupId, String userEmailToAdd, BigDecimal ownershipRatio) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String inviterEmail = authentication.getName();

        Users inviter = userRepository.findByEmail(inviterEmail)
                .orElseThrow(() -> new RuntimeException("Người mời (Inviter) không tồn tại."));

        Users userToAdd = userRepository.findByEmail(userEmailToAdd)
                .orElseThrow(() -> new RuntimeException("Email người dùng '" + userEmailToAdd + "' không tồn tại."));

        Groups group = groupUsersRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Nhóm (Group) không tồn tại."));

        GroupMemberId inviterMembershipId = new GroupMemberId(inviter.getUser_id(), group.getGroupId());
        if (!groupMembersRepository.existsById(inviterMembershipId)) {
            throw new RuntimeException("Bạn không có quyền thêm thành viên vào nhóm này.");
        }

        GroupMemberId newMemberId = new GroupMemberId(userToAdd.getUser_id(), group.getGroupId());
        if (groupMembersRepository.existsById(newMemberId)) {
            throw new RuntimeException("Người dùng này đã ở trong nhóm.");
        }

        GroupMembers newMember = new GroupMembers(
                userToAdd,
                group,
                ownershipRatio,
                LocalDateTime.now()
        );

        return groupMembersRepository.save(newMember);
    }
}