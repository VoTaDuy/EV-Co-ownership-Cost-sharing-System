package com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.CreateCostRequest;
import com.example.EV.Co_ownership.Cost_sharing.system.DTO.VehicleCostDTO;

import java.util.List;

public interface VehicleCostServiceImp {
    List<VehicleCostDTO> getAllByGroup(int groupId);

    VehicleCostDTO create(CreateCostRequest request, int userId, int groupId);
    VehicleCostDTO getById(Integer costId);
    VehicleCostDTO updateStatus(Integer costId, String status, int userId);
    void delete(Integer costId);
}