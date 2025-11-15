package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CostShareDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CreateShareRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.SettleShareRequest;

import java.util.List;

public interface CostShareServiceImp {
    List<CostShareDTO> getByCost(Integer costId);
    List<CostShareDTO> getByUser(int userId);
    List<CostShareDTO> createShares(CreateShareRequest request, int userId);
    CostShareDTO settleShare(Integer shareId, SettleShareRequest request, int userId);
    void deleteByCost(Integer costId);
}
