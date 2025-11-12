package com.example.EV.Co_ownership.Cost_sharing.System.Controller;


import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PaymentDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/group/{groupId}")
    public List<PaymentDTO> getByGroup(@PathVariable String groupId) {
        return paymentService.getByGroup(groupId);
    }

    @GetMapping("/user/{userId}")
    public List<PaymentDTO> getByUser(@PathVariable String userId) {
        return paymentService.getByUser(userId);
    }

    @GetMapping("/cost/{costId}")
    public List<PaymentDTO> getByCost(@PathVariable Integer costId) {
        return paymentService.getByCost(costId);
    }

    @GetMapping("/fund/{fundId}")
    public List<PaymentDTO> getByFund(@PathVariable Integer fundId) {
        return paymentService.getByFund(fundId);
    }

    @PostMapping
    public PaymentDTO create(@RequestBody Map<String, Object> request,
                             @RequestHeader("userId") String userId) {
        return paymentService.createPayment(request, userId);
    }

    @PostMapping("/callback")
    public ResponseEntity<String> callback(@RequestBody Map<String, String> payload) {
        String gatewayOrderId = payload.get("gatewayOrderId");
        String status = payload.get("status"); // tùy cổng thanh toán
        String response = payload.toString();

        paymentService.handleGatewayCallback(gatewayOrderId, status, response);
        return ResponseEntity.ok("OK");
    }
}