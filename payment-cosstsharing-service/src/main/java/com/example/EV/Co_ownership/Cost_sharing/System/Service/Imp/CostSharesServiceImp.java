package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CostSharesDTO;

import java.util.List;

public interface CostSharesServiceImp {
    CostSharesDTO getCostShareById(int share_id);
    List<CostSharesDTO> getAllCostShares();
    CostSharesDTO createCostShare(CostSharesDTO dto);
    CostSharesDTO updateCostShare(int share_id, CostSharesDTO dto);
    boolean deleteCostShare(int share_id);
}
