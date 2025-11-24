package com.example.EV.Co_ownership.Cost_sharing.system.Controller;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.CreateCostRequest;
import com.example.EV.Co_ownership.Cost_sharing.system.DTO.VehicleCostDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.VehicleCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment/costs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleCostController {

    private final VehicleCostService costService;

    @GetMapping
    public List<VehicleCostDTO> getAllByGroup(@RequestParam int groupId) {
        return costService.getAllByGroup(groupId);
    }

    @GetMapping("/fund/{fundId}")
    public List<VehicleCostDTO> getAllByFund(@PathVariable Integer fundId) {
        return costService.getAllByFund(fundId);
    }

    @GetMapping("/{id}")
    public VehicleCostDTO getById(@PathVariable Integer id) {
        return costService.getById(id);
    }

    @PostMapping
    public VehicleCostDTO create(@RequestBody CreateCostRequest request,
                                 @RequestHeader("userId") int userId) {
        return costService.create(request, userId);
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