package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleCostRepository extends JpaRepository<VehicleCost, Integer> {
    @Query("SELECT v FROM vehicle_cost v WHERE v.cost_id = :cost_id")
    VehicleCost getVehicleCostById(int cost_id);
}
