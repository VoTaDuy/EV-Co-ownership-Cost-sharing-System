package com.example.EV.Co_ownership.Cost_sharing.system.Repository;

import com.example.EV.Co_ownership.Cost_sharing.system.Entity.GroupFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupFundRepository extends JpaRepository<GroupFund, Integer> {
    List<GroupFund> findByGroupId(int groupId);
}