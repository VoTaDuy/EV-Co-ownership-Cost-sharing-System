package com.example.EV.Co_ownership.Cost_sharing.system.RepositoryData;


import com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {
    Vehicles findByVehicleId (int vehicleId);
}
