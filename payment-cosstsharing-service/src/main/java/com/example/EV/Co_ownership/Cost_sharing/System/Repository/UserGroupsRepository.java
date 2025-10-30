package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupsRepository extends JpaRepository<UserGroups, Integer> {

    @Query("SELECT ug FROM user_group ug WHERE ug.group_id.group_id = :groupId")
    List<UserGroups> findByGroupId(@Param("groupId") int groupId);

    @Query("SELECT u FROM user_group u WHERE u.user_id = :user_id")
    List<UserGroups> findByUser_id(@Param("user_id") int user_id);
}