package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleCostRepository extends JpaRepository<VehicleCost, Integer> {
    List<VehicleCost> findByGroupId(String groupId);
    List<VehicleCost> findByFund_FundId(Integer fundId);
}