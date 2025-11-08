package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.Embeddable.GroupMemberId;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.GroupMembers;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Groups;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Profiles;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.GroupMembersRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.GroupUsersRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.ProfileRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request.CreateGroupRequest;
import com.example.EV_Co_ownership.User_and_ownership_service.DTO.MemberDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupUsersRepository groupUsersRepository;

    @Autowired
    private GroupMembersRepository groupMembersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    public Groups createGroup(CreateGroupRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        Users creator = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng đã xác thực."));

        Groups newGroup = new Groups();
        newGroup.setGroupName(request.getGroupName());
        newGroup.setDescription(request.getDescription());
        newGroup.setCreatedDate(LocalDateTime.now());
        Groups savedGroup = groupUsersRepository.save(newGroup);

        GroupMembers firstMember = new GroupMembers(
                creator,
                savedGroup,
                null,
                LocalDateTime.now()
        );
        groupMembersRepository.save(firstMember);

        return savedGroup;
    }

    @Transactional
    public MemberDTO addOrUpdateMember(int groupId, int userIdToAdd, BigDecimal newRatio) {
        if (newRatio == null || newRatio.compareTo(BigDecimal.ZERO) < 0 || newRatio.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new RuntimeException("Tỷ lệ sở hữu phải từ 0% đến 100%");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String inviterEmail = authentication.getName();
        Users inviter = userRepository.findByEmail(inviterEmail)
                .orElseThrow(() -> new RuntimeException("Người mời không tồn tại."));

        Users userToAdd = userRepository.findById(userIdToAdd)
                .orElseThrow(() -> new RuntimeException("User ID '" + userIdToAdd + "' không tồn tại."));

        Groups group = groupUsersRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Nhóm không tồn tại."));

        GroupMemberId inviterMembershipId = new GroupMemberId(inviter.getUser_id(), group.getGroupId());
        if (!groupMembersRepository.existsById(inviterMembershipId)) {
            throw new RuntimeException("Bạn không có quyền thêm/cập nhật thành viên nhóm này.");
        }

        List<GroupMembers> members = groupMembersRepository.findAllByGroupId(groupId);

        final GroupMembers finalTargetMember;

        GroupMembers existingMember = members.stream()
                .filter(m -> m.getUser().getUser_id() == userIdToAdd)
                .findFirst()
                .orElse(null);

        if (existingMember == null) {
            finalTargetMember = new GroupMembers(userToAdd, group, newRatio, LocalDateTime.now());
            members.add(finalTargetMember);
        } else {
            existingMember.setOwnershipRatio(newRatio);
            finalTargetMember = existingMember;
        }

        List<GroupMembers> adjustableMembers = members.stream()
                .filter(m -> !m.equals(finalTargetMember) && m.getOwnershipRatio() != null && m.getOwnershipRatio().compareTo(BigDecimal.ZERO) > 0)
                .toList();

        BigDecimal remaining = BigDecimal.valueOf(100).subtract(newRatio);
        BigDecimal totalAdjustable = adjustableMembers.stream()
                .map(GroupMembers::getOwnershipRatio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal accumulated = BigDecimal.ZERO;
        for (int i = 0; i < adjustableMembers.size(); i++) {
            GroupMembers m = adjustableMembers.get(i);
            BigDecimal adjusted;
            if (totalAdjustable.compareTo(BigDecimal.ZERO) == 0) {
                adjusted = remaining.divide(BigDecimal.valueOf(adjustableMembers.size()), 2, BigDecimal.ROUND_HALF_UP);
            } else {
                adjusted = m.getOwnershipRatio().multiply(remaining).divide(totalAdjustable, 2, BigDecimal.ROUND_HALF_UP);
            }
            if (i == adjustableMembers.size() - 1) {
                adjusted = remaining.subtract(accumulated);
            }
            m.setOwnershipRatio(adjusted);
            accumulated = accumulated.add(adjusted);
        }

        groupMembersRepository.saveAll(members);

        Optional<Profiles> profileOpt = profileRepository.findProfileByUserId(userToAdd.getUser_id());
        String fullName = profileOpt.map(Profiles::getFull_name).orElse(userToAdd.getEmail());

        return new MemberDTO(
                finalTargetMember.getUser().getUser_id(),
                fullName,
                finalTargetMember.getOwnershipRatio()
        );
    }
}
