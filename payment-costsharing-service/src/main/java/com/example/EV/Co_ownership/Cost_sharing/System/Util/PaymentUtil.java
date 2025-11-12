package com.example.EV.Co_ownership.Cost_sharing.System.Util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentUtil {

    public static String createPaymentUrl(String orderId, BigDecimal amount, String description, String gateway) {
        return switch (gateway.toUpperCase()) {
            case "MOMO" -> buildMomoUrl(orderId, amount, description);
            case "VNPAY" -> buildVnpayUrl(orderId, amount, description);
            default -> throw new IllegalArgumentException("Cổng thanh toán không hỗ trợ: " + gateway);
        };
    }

    private static String buildMomoUrl(String orderId, BigDecimal amount, String description) {
        Map<String, String> params = new HashMap<>();
        params.put("partnerCode", "MOMO");
        params.put("orderId", orderId);
        params.put("amount", amount.toPlainString());
        params.put("orderInfo", description);
        params.put("redirectUrl", "https://yourdomain.com/api/funds/callback");
        params.put("ipnUrl", "https://yourdomain.com/api/funds/callback");
        return "https://test-payment.momo.vn/v2/gateway/api/create?" + mapToQuery(params);
    }

    private static String buildVnpayUrl(String orderId, BigDecimal amount, String description) {
        return "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?vnp_OrderInfo=" + description +
                "&vnp_Amount=" + amount.multiply(BigDecimal.valueOf(100)).longValue();
    }

    private static String mapToQuery(Map<String, String> map) {
        return map.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .reduce((a, b) -> a + "&" + b)
                .orElse("");
    }
}