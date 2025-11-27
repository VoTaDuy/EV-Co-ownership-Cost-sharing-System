package com.example.EV.Co_ownership.Cost_sharing.system.Controller;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.VehicleDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse.Vehicles;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.VehicleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/vehicle")
public class VehicleController {


    @Autowired
    VehicleServiceImp vehicleServiceImp;

    @GetMapping("/getName/{vehicleId}")
    public ResponseEntity<?> getVehicleName(@PathVariable int vehicleId){

        VehicleDTO  vehicleDTO = vehicleServiceImp.findVehicleByVehicleId(vehicleId);


        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
    }
}
