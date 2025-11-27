package com.example.EV.Co_ownership.Cost_sharing.system.Controller;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.CreateCostRequest;
import com.example.EV.Co_ownership.Cost_sharing.system.DTO.VehicleCostDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.VehicleCostServiceImp;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.VehicleCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment/costs")
public class VehicleCostController {


    @Autowired
    private VehicleCostServiceImp costService;
    @GetMapping
    public List<VehicleCostDTO> getAllByGroup(@RequestParam int groupId) {
        return costService.getAllByGroup(groupId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCostDTO(){
        return new ResponseEntity<>( costService.getAllVehicleCost(),HttpStatus.OK);
    }
        

    @GetMapping("/costId/{id}")
    public VehicleCostDTO getById(@PathVariable Integer id) {
        return costService.getById(id);
    }

    @PostMapping("/{groupId}/{userId}")
    public VehicleCostDTO create(@RequestBody CreateCostRequest request,
                                 @PathVariable int groupId,
                                 @PathVariable int userId) {
        return costService.create(request, groupId, userId);
    }

    @PatchMapping("/{id}/status")
    public VehicleCostDTO updateStatus(@PathVariable Integer id,
                                       @RequestBody UpdateStatusRequest request,
                                       @RequestHeader("userId") int userId) {
        return costService.updateStatus(id, request.status(), userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        costService.delete(id);
        return ResponseEntity.noContent().build();
    }
}