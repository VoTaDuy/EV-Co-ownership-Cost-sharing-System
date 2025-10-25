package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.VehicleCostDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;

import java.util.List;

public interface VehicleCostServiceImp {
    VehicleCostDTO getVehicleCostById(int cost_id);
    List<VehicleCostDTO> getAllVehicleCost();

    VehicleCostDTO createVehicleCost(VehicleCostDTO dto);

    VehicleCostDTO updateVehicleCost(int cost_id, VehicleCostDTO dto);

    boolean deleteVehicleCost(int cost_id);
}
