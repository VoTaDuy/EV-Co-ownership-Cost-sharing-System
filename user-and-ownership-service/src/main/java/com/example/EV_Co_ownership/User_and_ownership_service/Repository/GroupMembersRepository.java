package com.example.EV_Co_ownership.User_and_ownership_service.Repository;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.GroupMembers;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.GroupMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMembersRepository extends JpaRepository<GroupMembers, GroupMemberId> {
    List<GroupMembers> findByUser_UserId(int userId);
    List<GroupMembers> findByGroup_GroupId(int groupId);
}
