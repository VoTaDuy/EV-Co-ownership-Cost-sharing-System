package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CreateCostRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.VehicleCostDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.VehicleCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/costs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleCostController {

    private final VehicleCostService costService;

    @GetMapping
    public List<VehicleCostDTO> getAllByGroup(@RequestParam String groupId) {
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
                                 @RequestHeader("userId") String userId) {
        return costService.create(request, userId);
    }

    @PatchMapping("/{id}/status")
    public VehicleCostDTO updateStatus(@PathVariable Integer id,
                                       @RequestBody UpdateStatusRequest request,
                                       @RequestHeader("userId") String userId) {
        return costService.updateStatus(id, request.status(), userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        costService.delete(id);
        return ResponseEntity.noContent().build();
    }
}