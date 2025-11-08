package com.example.EV.Co_ownership.Cost_sharing.System.Controller;


import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;

import com.example.EV.Co_ownership.Cost_sharing.System.Service.GroupFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groupfunds")
@CrossOrigin(origins = "http://localhost:3000")

public class GroupFundController {

    @Autowired
    private GroupFundService groupFundService;

    @GetMapping("/all")
    public List<GroupFund> getAllFunds() {
        return groupFundService.getAllFunds();
    }

    @GetMapping("/{id}")
    public GroupFund getFundById(@PathVariable int id) {
        return groupFundService.getFundById(id);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupFund>> getFundsByGroup(@PathVariable("groupId") String groupId) {
        List<GroupFund> funds = groupFundService.getFundsByGroupId(groupId);
        return ResponseEntity.ok(funds);
    }

    @PostMapping("/create")
    public GroupFund createFund(@RequestBody GroupFund fund) {
        return groupFundService.createFund(fund);
    }

    @PutMapping("/{id}")
    public GroupFund updateFund(@PathVariable int id, @RequestBody GroupFund fund) {
        return groupFundService.updateFund(id, fund);
    }

    @DeleteMapping("/{id}")
    public void deleteFund(@PathVariable int id) {
        groupFundService.deleteFund(id);
    }
}