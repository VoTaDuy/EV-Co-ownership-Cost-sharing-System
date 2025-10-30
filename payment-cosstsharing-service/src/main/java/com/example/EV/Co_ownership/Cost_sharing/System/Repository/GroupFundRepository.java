package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupFundRepository extends JpaRepository<GroupFund, Integer> {
    @Query("SELECT g FROM group_fund g WHERE g.group_id.group_id = :groupId")
    List<GroupFund> findByGroupId(@Param("group_id") int groupId);

    @Query("SELECT g FROM group_fund g WHERE g.fund_id = :fundId")
    GroupFund findByFund_id(@Param("fund_id") int fundId);

    @Query("SELECT ug FROM user_group ug WHERE ug.group_id.group_id = :groupId AND ug.user_id = :userId")
    Optional<UserGroups> findUserInGroup(@Param("groupId") int groupId, @Param("userId") int userId);

}