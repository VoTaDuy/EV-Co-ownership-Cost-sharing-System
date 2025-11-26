package com.example.EV.Co_ownership.Cost_sharing.system.Controller;


import com.example.EV.Co_ownership.Cost_sharing.system.DTO.OwnershipGroupDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse.OwnershipGroup;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.OwnershipGroupServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/ownership")
@CrossOrigin(origins = "http://localhost:3000")

public class OwnershipGroupController {

    @Autowired
    private OwnershipGroupServiceImp ownershipGroupServiceImp;

    @GetMapping("{groupId}")
    public ResponseEntity<?> getDetailGroup(@PathVariable int groupId){
        OwnershipGroupDTO ownershipGroupDTO = ownershipGroupServiceImp.getDetailGroup(groupId);

        return new ResponseEntity<>(ownershipGroupDTO, HttpStatus.OK);
    }
}
