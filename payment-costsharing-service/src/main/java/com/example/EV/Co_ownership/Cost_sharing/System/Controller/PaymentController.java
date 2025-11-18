package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PaymentDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Exception.NotFoundException;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.PaymentService;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.VnPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
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

    // === LẤY DỮ LIỆU ===
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

    // === TẠO THANH TOÁN BÌNH THƯỜNG ===
    @PostMapping
    public PaymentDTO create(@RequestBody Map<String, Object> request,
                             @RequestHeader("userId") int userId) {
        return paymentService.createPayment(request, userId);
    }

    // === CALLBACK TỪ CỔNG THANH TOÁN ===
    @PostMapping("/callback")
    public ResponseEntity<String> callback(@RequestBody Map<String, String> payload) {
        String gatewayOrderId = payload.get("gatewayOrderId");
        String status = payload.get("status");
        String response = payload.toString();
        paymentService.handleGatewayCallback(gatewayOrderId, status, response);
        return ResponseEntity.ok("OK");
    }

    // === TẠO URL VNPAY ===
    @PostMapping("/vnpay")
    public ResponseEntity<?> createVnpayPayment(@RequestBody Map<String, Object> req) throws Exception {
        String orderId = "ORDER" + System.currentTimeMillis();
        Long amount = Long.parseLong(req.get("amount").toString());
        String content = "Payment for group " + req.get("groupId");
        String url = vnPayService.createPaymentUrl(orderId, amount, content);
        return ResponseEntity.ok(Map.of("paymentUrl", url, "orderId", orderId));
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

    // === GIẢ LẬP TẠO + THÀNH CÔNG NGAY (DEV) ===
    @Profile({"dev", "test"})
    @PostMapping("/fake-create")
    public ResponseEntity<?> createAndFakeSuccess(
            @RequestBody Map<String, Object> request,
            @RequestHeader(value = "userId", required = false) Integer userIdHeader) {

        try {
            // BẮT LỖI: userId phải có trong HEADER
            if (userIdHeader == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "error", "Thiếu header bắt buộc: userId",
                        "hint", "Thêm header 'userId: 10' vào request (KHÔNG ĐƯỢC ĐẶT TRONG BODY)"
                ));
            }

            // XÓA userId khỏi body nếu frontend gửi nhầm
            request.remove("userId");

            PaymentDTO created = paymentService.createPayment(request, userIdHeader);
            PaymentDTO result = paymentService.handleFakePaymentSuccess(created.getGatewayOrderId());
            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Lỗi server: " + e.getMessage(),
                    "hint", "Kiểm tra userId phải ở HEADER, không được ở body"
            ));
        }
    }

    // === GIẢ LẬP THÀNH CÔNG CHỈ (CHO THANH TOÁN ĐÃ TỒN TẠI) ===
    @Profile({"dev", "test"})
    @PostMapping("/fake")
    public ResponseEntity<?> fakeSuccess(@RequestBody Map<String, Object> request) {
        try {
            String gatewayOrderId = (String) request.get("gatewayOrderId");
            if (gatewayOrderId == null || gatewayOrderId.isBlank()) {
                return ResponseEntity.badRequest().body(Map.of(
                        "error", "Thiếu trường bắt buộc: gatewayOrderId"
                ));
            }

            PaymentDTO result = paymentService.handleFakePaymentSuccess(gatewayOrderId);
            return ResponseEntity.ok(result);

        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(Map.of("error", "Không tìm thấy thanh toán: " + e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Lỗi khi giả lập: " + e.getMessage(),
                    "hint", "Kiểm tra gatewayOrderId có tồn tại và userId trong DB không null"
            ));
        }
    }
}