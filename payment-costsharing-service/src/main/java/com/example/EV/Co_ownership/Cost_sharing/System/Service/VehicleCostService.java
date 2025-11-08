package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.VehicleCostDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.VehicleCostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class VehicleCostService implements VehicleCostServiceImp {

    @Autowired
    VehicleCostRepository vehicleCostRepository;

    @Override
    public VehicleCostDTO getVehicleCostById(int cost_id) {

        VehicleCost vehicleCost = vehicleCostRepository.getVehicleCostById(cost_id);
        if (vehicleCost == null) {
            return null;
        }
        VehicleCostDTO vehicleCostDTO = new VehicleCostDTO();
        vehicleCostDTO.setCost_id(vehicleCost.getCost_id());
        vehicleCostDTO.setCost_name(vehicleCost.getCost_name());
        vehicleCostDTO.setVehicle_id(vehicleCost.getVehicle_id());
        vehicleCostDTO.setGroup_id(vehicleCost.getGroup_id());
        vehicleCostDTO.setAmount(vehicleCost.getAmount());
        vehicleCostDTO.setStatus(vehicleCost.getStatus());
        vehicleCostDTO.setCreated_at(vehicleCost.getCreated_at());
        vehicleCostDTO.setUpdated_at(vehicleCost.getUpdated_at());
        return vehicleCostDTO;
    }

    @Override
    public List<VehicleCostDTO> getAllVehicleCost() {
        List<VehicleCost> entityList = vehicleCostRepository.findAll();
        List<VehicleCostDTO> vehicleCostDTOList = new ArrayList<>();

        for (VehicleCost vehicleCost : entityList) {
            VehicleCostDTO vehicleCostDTO = new VehicleCostDTO();
            vehicleCostDTO.setCost_id(vehicleCost.getCost_id());
            vehicleCostDTO.setCost_name(vehicleCost.getCost_name());
            vehicleCostDTO.setVehicle_id(vehicleCost.getVehicle_id());
            vehicleCostDTO.setGroup_id(vehicleCost.getGroup_id());
            vehicleCostDTO.setAmount(vehicleCost.getAmount());
            vehicleCostDTO.setStatus(vehicleCost.getStatus());
            vehicleCostDTO.setCreated_at(vehicleCost.getCreated_at());
            vehicleCostDTO.setUpdated_at(vehicleCost.getUpdated_at());
            vehicleCostDTOList.add(vehicleCostDTO);
        }
        return vehicleCostDTOList;
    }


    @Override
    public VehicleCostDTO createVehicleCost(VehicleCostDTO dto) {
        VehicleCost vehicleCost = new VehicleCost();
        vehicleCost.setCost_name(dto.getCost_name());
        vehicleCost.setVehicle_id(dto.getVehicle_id());
        vehicleCost.setGroup_id(dto.getGroup_id());
        vehicleCost.setAmount(dto.getAmount());
        vehicleCost.setStatus(dto.getStatus());
        vehicleCost.setCreated_at(dto.getCreated_at());
        vehicleCost.setUpdated_at(dto.getUpdated_at());

        VehicleCost saved = vehicleCostRepository.save(vehicleCost);

        VehicleCostDTO savedDTO = new VehicleCostDTO();
        savedDTO.setCost_id(saved.getCost_id());
        savedDTO.setCost_name(saved.getCost_name());
        savedDTO.setVehicle_id(saved.getVehicle_id());
        savedDTO.setGroup_id(saved.getGroup_id());
        savedDTO.setAmount(saved.getAmount());
        savedDTO.setStatus(saved.getStatus());
        savedDTO.setCreated_at(saved.getCreated_at());
        savedDTO.setUpdated_at(saved.getUpdated_at());
        return savedDTO;
    }

    @Override
    public VehicleCostDTO updateVehicleCost(int cost_id, VehicleCostDTO dto) {
        Optional<VehicleCost> optional = vehicleCostRepository.findById(cost_id);
        if (optional.isEmpty()) {
            return null;
        }
        VehicleCost vehicleCost = optional.get();
        vehicleCost.setCost_name(dto.getCost_name());
        vehicleCost.setVehicle_id(dto.getVehicle_id());
        vehicleCost.setGroup_id(dto.getGroup_id());
        vehicleCost.setAmount(dto.getAmount());
        vehicleCost.setStatus(dto.getStatus());
        vehicleCost.setUpdated_at(dto.getUpdated_at());

        VehicleCost updated = vehicleCostRepository.save(vehicleCost);

        VehicleCostDTO updatedDTO = new VehicleCostDTO();
        updatedDTO.setCost_id(updated.getCost_id());
        updatedDTO.setCost_name(updated.getCost_name());
        updatedDTO.setVehicle_id(updated.getVehicle_id());
        updatedDTO.setGroup_id(updated.getGroup_id());
        updatedDTO.setAmount(updated.getAmount());
        updatedDTO.setStatus(updated.getStatus());
        updatedDTO.setCreated_at(updated.getCreated_at());
        updatedDTO.setUpdated_at(updated.getUpdated_at());
        return updatedDTO;
    }

    @Override
    public boolean deleteVehicleCost(int cost_id) {
        if (!vehicleCostRepository.existsById(cost_id)) {
            return false;
        }
        vehicleCostRepository.deleteById(cost_id);
        return true;
    }
}