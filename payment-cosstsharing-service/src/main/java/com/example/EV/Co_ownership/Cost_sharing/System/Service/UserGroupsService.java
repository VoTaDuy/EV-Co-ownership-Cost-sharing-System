package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupTable;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.UserGroups;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.UserGroupRole;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.GroupTableRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.UserGroupsRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.UserGroupsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroupsService implements UserGroupsServiceImp {
    @Autowired
    private UserGroupsRepository userGroupsRepository;

    @Override
    public List<UserGroups> getAllUserGroups() {
        return userGroupsRepository.findAll();
    }

    @Override
    public UserGroups getUserGroupById(int id) {
        Optional<UserGroups> userGroup = userGroupsRepository.findById(id);
        return userGroup.orElse(null);
    }

    @Override
    public List<UserGroups> getUserGroupsByGroupId(int group_id) {
        return userGroupsRepository.findByGroup_id(group_id);
    }

    @Override
    public List<UserGroups> getUserGroupsByUserId(int userId) {
        return userGroupsRepository.findByUser_id(userId);
    }

    @Override
    public UserGroups createUserGroup(UserGroups userGroup) {
        return userGroupsRepository.save(userGroup);
    }

    @Override
    public UserGroups updateUserGroup(int id, UserGroups userGroup) {
        UserGroups existing = userGroupsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user_group ID = " + id));

        existing.setGroup_id(userGroup.getGroup_id());
        existing.setUser_id(userGroup.getUser_id());
        existing.setRole(userGroup.getRole());
        existing.setUpdated_at(LocalDateTime.now());

        return userGroupsRepository.save(existing);
    }

    @Override
    public void deleteUserGroup(int id) {
        userGroupsRepository.deleteById(id);
    }
}