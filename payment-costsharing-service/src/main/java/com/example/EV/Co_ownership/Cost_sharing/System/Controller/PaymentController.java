package com.example.EV.Co_ownership.Cost_sharing.System.Controller;


import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PaymentDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.PaymentService;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.VnPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    private final PaymentService paymentService;
    private final VnPayService vnPayService;


    @GetMapping("/group/{groupId}")
    public List<PaymentDTO> getByGroup(@PathVariable int groupId) {
        return paymentService.getByGroup(groupId);
    }

    @GetMapping("/user/{userId}")
    public List<PaymentDTO> getByUser(@PathVariable int userId) {
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
                             @RequestHeader("userId") int userId) {
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

    @PostMapping("/vnpay")
    public ResponseEntity<?> createVnpayPayment(@RequestBody Map<String, Object> req) throws Exception {
        String orderId = "ORDER" + System.currentTimeMillis();
        Long amount = Long.parseLong(req.get("amount").toString());
        String content = "Payment for group " + req.get("groupId");

        String url = vnPayService.createPaymentUrl(orderId, amount, content);

        return ResponseEntity.ok(Map.of(
                "paymentUrl", url,
                "orderId", orderId
        ));
    }

    @GetMapping("/vnpay-return")
    public ResponseEntity<?> vnpayReturn(@RequestParam Map<String, String> params) {
        String status = params.get("vnp_ResponseCode");
        String txnRef = params.get("vnp_TxnRef");

        if ("00".equals(status)) {
            paymentService.handleGatewayCallback(txnRef, "completed", params.toString());
            return ResponseEntity.ok("Thanh toán thành công");
        } else {
            paymentService.handleGatewayCallback(txnRef, "failed", params.toString());
            return ResponseEntity.ok("Thanh toán thất bại");
        }
    }
}