package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CreateFundRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.DepositRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.FundTransactionDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.GroupFundDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.GroupFundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment/funds")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class GroupFundController {

    private final GroupFundService fundService;

    @GetMapping
    public List<GroupFundDTO> getAll(@RequestParam(required = false) Integer groupId) {
        if (groupId != null) {
            return fundService.getAll(groupId);
        }
        return List.of();
    }

    @GetMapping("/{id}")
    public GroupFundDTO getById(@PathVariable int id) {
        return fundService.getById(id);
    }

    @GetMapping("/{id}/transactions")
    public List<FundTransactionDTO> getTransactions(@PathVariable int id) {
        return fundService.getTransactions(id);
    }

    @PostMapping
    public GroupFundDTO create(@RequestBody CreateFundRequest request,
                               @RequestHeader("userId") String userIdHeader) {
        int userId = Integer.parseInt(userIdHeader); // parse String -> int
        return fundService.create(request, userId);
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