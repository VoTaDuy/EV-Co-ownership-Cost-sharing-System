package com.example.EV.Co_ownership.Cost_sharing.system.Service;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.VehicleDTO;

import com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse.Vehicles;
import com.example.EV.Co_ownership.Cost_sharing.system.RepositoryData.VehicleRepository;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.VehicleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleService implements VehicleServiceImp {


    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public VehicleDTO findVehicleByVehicleId(int vehicleId) {

        Vehicles vehicles = vehicleRepository.findByVehicleId(vehicleId);
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setVehicleId(vehicles.getVehicleId());
        vehicleDTO.setVehicleName(vehicles.getVehicleName());
        return vehicleDTO;
    }
}
