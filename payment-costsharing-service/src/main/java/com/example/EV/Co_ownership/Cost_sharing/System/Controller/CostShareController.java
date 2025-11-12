package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CostShareDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CreateShareRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.SettleShareRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.CostShareServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/costshares")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CostShareController {

    private final CostShareServiceImp shareService;

    @GetMapping
    public List<CostShareDTO> getAll(@RequestParam(required = false) Integer costId) {
        if (costId != null) {
            return shareService.getByCost(costId);
        }
        return List.of();
    }

    @GetMapping("/user/{userId}")
    public List<CostShareDTO> getByUser(@PathVariable String userId) {
        return shareService.getByUser(userId);
    }

    @PostMapping
    public List<CostShareDTO> createShares(
            @RequestBody CreateShareRequest request,
            @RequestHeader("userId") String userId) {
        return shareService.createShares(request, userId);
    }

    @PostMapping("/{id}/settle")
    public CostShareDTO settleShare(
            @PathVariable Integer id,
            @RequestBody SettleShareRequest request,
            @RequestHeader("userId") String userId) {
        return shareService.settleShare(id, request, userId);
    }

    @DeleteMapping("/by-cost/{costId}")
    public ResponseEntity<Void> deleteByCost(@PathVariable Integer costId) {
        shareService.deleteByCost(costId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/callback")
    public ResponseEntity<String> callback(@RequestBody Map<String, String> payload) {
        System.out.println("Received callback payload: " + payload);
        return ResponseEntity.ok("OK");
    }
}
