package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CreateCostRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.VehicleCostDTO;

import java.util.List;

public interface VehicleCostServiceImp {
    List<VehicleCostDTO> getAllByGroup(String groupId);
    List<VehicleCostDTO> getAllByFund(Integer fundId);
    VehicleCostDTO create(CreateCostRequest request, String userId);
    VehicleCostDTO getById(Integer costId);
    VehicleCostDTO updateStatus(Integer costId, String status, String userId);
    void delete(Integer costId);
}