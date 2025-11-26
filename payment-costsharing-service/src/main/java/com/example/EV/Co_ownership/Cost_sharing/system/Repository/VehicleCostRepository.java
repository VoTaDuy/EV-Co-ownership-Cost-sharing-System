package com.example.EV.Co_ownership.Cost_sharing.system.Repository;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.VehicleCostDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.Entity.VehicleCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleCostRepository extends JpaRepository<VehicleCost, Integer> {
    List<VehicleCost> findByGroupId(int groupId);
}