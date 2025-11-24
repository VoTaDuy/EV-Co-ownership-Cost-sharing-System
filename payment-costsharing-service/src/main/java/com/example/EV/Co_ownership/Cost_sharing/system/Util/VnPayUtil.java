package com.example.EV.Co_ownership.Cost_sharing.system.Util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VnPayUtil {

    public static String hmacSHA512(String key, String data) throws Exception {
        Mac hmac = Mac.getInstance("HmacSHA512");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmac.init(secret_key);
        return bytesToHex(hmac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static String buildPaymentUrl(Map<String, String> params, String hashSecret, String baseUrl) throws Exception {
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for (String field : fieldNames) {
            String value = params.get(field);
            if (value != null && value.length() > 0) {
                hashData.append(field).append("=").append(URLEncoder.encode(value, StandardCharsets.US_ASCII));
                query.append(URLEncoder.encode(field, StandardCharsets.US_ASCII))
                        .append("=")
                        .append(URLEncoder.encode(value, StandardCharsets.US_ASCII));
                if (!field.equals(fieldNames.get(fieldNames.size() - 1))) {
                    hashData.append("&");
                    query.append("&");
                }
            }
        }

        String signature = hmacSHA512(hashSecret, hashData.toString());
        query.append("&vnp_SecureHash=").append(signature);

        return baseUrl + "?" + query;
    }
}
