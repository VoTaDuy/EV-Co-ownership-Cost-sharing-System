package com.example.EV.Co_ownership.Cost_sharing.System.Controller;


import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupTable;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.GroupTableRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.GroupFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/groupfunds")
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
    public List<GroupFund> getFundsByGroupId(@PathVariable int groupId) {
        return groupFundService.getFundsByGroupId(groupId);
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