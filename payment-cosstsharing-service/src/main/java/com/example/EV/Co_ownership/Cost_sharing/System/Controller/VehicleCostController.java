package com.example.EV.Co_ownership.Cost_sharing.System.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/api/cost")
public class VehicleCostController {

    @GetMapping("/getCost")
    public ResponseEntity<?> getCost(@RequestParam int cost_id)
    {

        return ResponseEntity.ok().body(cost_id);
    }
}
