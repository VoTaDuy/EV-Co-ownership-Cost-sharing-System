package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CostSharesDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.CostShares;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.CostSharesRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.CostSharesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CostSharesService implements CostSharesServiceImp {

    @Autowired
    private CostSharesRepository costSharesRepository;

    @Autowired
    private VehicleCostRepository vehicleCostRepository;

    @Override
    public CostSharesDTO getCostShareById(int share_id) {
        CostShares costShare = costSharesRepository.getCostShareById(share_id);
        if (costShare == null) {
            return null;
        }
        CostSharesDTO costSharesDTO = new CostSharesDTO();
        costSharesDTO.setShare_id(costShare.getShare_id());
        costSharesDTO.setCost_id(costShare.getCost_id().getCost_id());
        costSharesDTO.setUser_id(costShare.getUser_id());
        costSharesDTO.setShare_percentage(costShare.getShare_percentage());
        costSharesDTO.setAmount_due(costShare.getAmount_due());
        costSharesDTO.setSettled_amount(costShare.getSettled_amount());
        costSharesDTO.setStatus(costShare.getStatus());
        return costSharesDTO;
    }

    @Override
    public List<CostSharesDTO> getAllCostShares() {
        List<CostShares> entityList = costSharesRepository.findAll();
        List<CostSharesDTO> costSharesDTOList = new ArrayList<>();

        for (CostShares costShare : entityList) {
            CostSharesDTO costSharesDTO = new CostSharesDTO();
            costSharesDTO.setShare_id(costShare.getShare_id());
            costSharesDTO.setCost_id(costShare.getCost_id().getCost_id());
            costSharesDTO.setUser_id(costShare.getUser_id());
            costSharesDTO.setShare_percentage(costShare.getShare_percentage());
            costSharesDTO.setAmount_due(costShare.getAmount_due());
            costSharesDTO.setSettled_amount(costShare.getSettled_amount());
            costSharesDTO.setStatus(costShare.getStatus());
            costSharesDTOList.add(costSharesDTO);
        }
        return costSharesDTOList;
    }

    @Override
    public CostSharesDTO createCostShare(CostSharesDTO dto) {
        VehicleCost vehicleCost = vehicleCostRepository.getVehicleCostById(dto.getCost_id());
        if (vehicleCost == null) {
            return null;
        }

        CostShares costShare = new CostShares();
        costShare.setCost_id(vehicleCost);
        costShare.setUser_id(dto.getUser_id());
        costShare.setShare_percentage(dto.getShare_percentage());
        costShare.setAmount_due(dto.getAmount_due());
        costShare.setSettled_amount(dto.getSettled_amount());
        costShare.setStatus(dto.getStatus());

        CostShares saved = costSharesRepository.save(costShare);

        CostSharesDTO savedDTO = new CostSharesDTO();
        savedDTO.setShare_id(saved.getShare_id());
        savedDTO.setCost_id(saved.getCost_id().getCost_id());
        savedDTO.setUser_id(saved.getUser_id());
        savedDTO.setShare_percentage(saved.getShare_percentage());
        savedDTO.setAmount_due(saved.getAmount_due());
        savedDTO.setSettled_amount(saved.getSettled_amount());
        savedDTO.setStatus(saved.getStatus());
        return savedDTO;
    }

    @Override
    public CostSharesDTO updateCostShare(int share_id, CostSharesDTO dto) {
        Optional<CostShares> optional = costSharesRepository.findById(share_id);
        if (optional.isEmpty()) {
            return null;
        }

        VehicleCost vehicleCost = vehicleCostRepository.getVehicleCostById(dto.getCost_id());
        if (vehicleCost == null) {
            return null;
        }

        CostShares costShare = optional.get();
        costShare.setCost_id(vehicleCost);
        costShare.setUser_id(dto.getUser_id());
        costShare.setShare_percentage(dto.getShare_percentage());
        costShare.setAmount_due(dto.getAmount_due());
        costShare.setSettled_amount(dto.getSettled_amount());
        costShare.setStatus(dto.getStatus());

        CostShares updated = costSharesRepository.save(costShare);

        CostSharesDTO updatedDTO = new CostSharesDTO();
        updatedDTO.setShare_id(updated.getShare_id());
        updatedDTO.setCost_id(updated.getCost_id().getCost_id());
        updatedDTO.setUser_id(updated.getUser_id());
        updatedDTO.setShare_percentage(updated.getShare_percentage());
        updatedDTO.setAmount_due(updated.getAmount_due());
        updatedDTO.setSettled_amount(updated.getSettled_amount());
        updatedDTO.setStatus(updated.getStatus());
        return updatedDTO;
    }

    @Override
    public boolean deleteCostShare(int share_id) {
        if (!costSharesRepository.existsById(share_id)) {
            return false;
        }
        costSharesRepository.deleteById(share_id);
        return true;
    }
}
