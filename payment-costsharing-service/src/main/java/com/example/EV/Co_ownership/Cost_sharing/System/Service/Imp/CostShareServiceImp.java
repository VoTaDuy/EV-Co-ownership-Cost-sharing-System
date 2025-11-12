package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CostShareDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CreateShareRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.SettleShareRequest;

import java.util.List;

public interface CostShareServiceImp {
    List<CostShareDTO> getByCost(Integer costId);
    List<CostShareDTO> getByUser(String userId);
    List<CostShareDTO> createShares(CreateShareRequest request, String userId);
    CostShareDTO settleShare(Integer shareId, SettleShareRequest request, String userId);
    void deleteByCost(Integer costId);
}
