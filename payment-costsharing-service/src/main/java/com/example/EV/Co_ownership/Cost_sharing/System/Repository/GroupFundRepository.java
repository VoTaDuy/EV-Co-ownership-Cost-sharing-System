package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupFundRepository extends JpaRepository<GroupFund, Integer> {
    List<GroupFund> findByGroupId(String groupId);

    @Query("SELECT g FROM group_fund g WHERE g.fund_id = :fundId")
    GroupFund findByFund_id(@Param("fund_id") int fundId);


}