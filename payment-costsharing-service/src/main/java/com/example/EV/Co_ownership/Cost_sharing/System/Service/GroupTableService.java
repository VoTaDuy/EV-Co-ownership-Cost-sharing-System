package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupTable;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.UserGroups;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.UserGroupRole;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.GroupTableRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.UserGroupsRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.GroupTableServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupTableService implements GroupTableServiceImp {

    @Autowired
    private GroupTableRepository groupTableRepository;

    @Autowired
    private UserGroupsRepository userGroupsRepository;

    @Override
    public GroupTable createGroup(GroupTable group) {
        group.setCreated_at(LocalDateTime.now());
        group.setUpdated_at(LocalDateTime.now());

        GroupTable savedGroup = groupTableRepository.save(group);

        UserGroups userGroup = new UserGroups();
        userGroup.setGroup_id(savedGroup);
        userGroup.setUser_id(group.getCreated_by());
        userGroup.setRole(UserGroupRole.ADMIN);
        userGroup.setCreated_at(LocalDateTime.now());
        userGroup.setUpdated_at(LocalDateTime.now());

        userGroupsRepository.save(userGroup);

        return savedGroup;
    }

    @Override
    public List<GroupTable> getAllGroups() {
        return groupTableRepository.findAll();
    }

    @Override
    public GroupTable getGroupById(int id) {
        return groupTableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm với ID = " + id));
    }

    @Override
    public GroupTable updateGroup(int id, GroupTable updatedGroup) {
        GroupTable group = groupTableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm với ID = " + id));

        group.setGroup_name(updatedGroup.getGroup_name());
        group.setDescription(updatedGroup.getDescription());
        group.setUpdated_at(LocalDateTime.now());

        return groupTableRepository.save(group);
    }

    @Override
    public void deleteGroup(int id) {
        if (!groupTableRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy nhóm với ID = " + id);
        }
        groupTableRepository.deleteById(id);
    }

}
