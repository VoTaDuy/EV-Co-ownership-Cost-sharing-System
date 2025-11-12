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
@RequestMapping("/api/funds")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class GroupFundController {

    private final GroupFundService fundService;

    @GetMapping
    public List<GroupFundDTO> getAll(@RequestParam(required = false) String groupId) {
        if (groupId != null && !groupId.isBlank()) {
            return fundService.getAll(groupId);
        }
        return fundService.getAll(groupId);
    }

    @GetMapping("/{id}")
    public GroupFundDTO getById(@PathVariable Integer id) {
        return fundService.getById(id);
    }

    @GetMapping("/{id}/transactions")
    public List<FundTransactionDTO> getTransactions(@PathVariable Integer id) {
        return fundService.getTransactions(id);
    }

    @PostMapping
    public GroupFundDTO create(@RequestBody CreateFundRequest request,
                               @RequestHeader("userId") String userId) {
        return fundService.create(request, userId);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Map<String, String>> deposit(
            @PathVariable Integer id,
            @RequestBody DepositRequest request,
            @RequestHeader("userId") String userId) {

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
            @PathVariable Integer id,
            @RequestHeader("userId") String userId) {
        fundService.deleteFund(id, userId);
        return ResponseEntity.noContent().build();
    }
}