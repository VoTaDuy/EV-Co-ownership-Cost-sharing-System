package com.example.EV.Co_ownership.Cost_sharing.system.Controller;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.CreateFundRequest;
import com.example.EV.Co_ownership.Cost_sharing.system.DTO.DepositRequest;
import com.example.EV.Co_ownership.Cost_sharing.system.DTO.FundTransactionDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.DTO.GroupFundDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.GroupFundService;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.GroupFundServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment/funds")
@RequiredArgsConstructor
public class GroupFundController {

    @Autowired
    private GroupFundServiceImp fundService;

    @GetMapping("/{groupId}")
    public List<GroupFundDTO> getAll(@PathVariable Integer groupId) {
        return fundService.getAll(groupId);
    }

    @GetMapping("/getFund/{id}")
    public GroupFundDTO getById(@PathVariable int id) {
        return fundService.getById(id);
    }

    @GetMapping("/{id}/transactions")
    public List<FundTransactionDTO> getTransactions(@PathVariable int id) {
        return fundService.getTransactions(id);
    }

    @PostMapping("/{groupId}/{userId}")
    public ResponseEntity<?> createFund(@RequestBody CreateFundRequest createFundRequest,
                                        @PathVariable int userId,
                                        @PathVariable int groupId){


        return new ResponseEntity<>(fundService.create(createFundRequest, userId, groupId),HttpStatus.OK);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Map<String, String>> deposit(
            @PathVariable int id,
            @RequestBody DepositRequest request,
            @RequestHeader("userId") String userIdHeader) {

        int userId = Integer.parseInt(userIdHeader);
        String paymentUrl = fundService.initiateDeposit(id, request, userId);
        return ResponseEntity.ok(Map.of("paymentUrl", paymentUrl));
    }

    @PostMapping("/callback")
    public ResponseEntity<String> callback(@RequestBody Map<String, String> payload) {
        String orderId = payload.get("orderId");
        String status = payload.get("resultCode");
        if (status == null) status = payload.get("vnp_ResponseCode");
        String txId = payload.get("transId");
        String response = payload.toString();

        fundService.handleDepositCallback(orderId, status, txId, response);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFund(
            @PathVariable int id,
            @RequestHeader("userId") String userIdHeader) {

        int userId = Integer.parseInt(userIdHeader);
        fundService.deleteFund(id, userId);
        return ResponseEntity.noContent().build();
    }
}