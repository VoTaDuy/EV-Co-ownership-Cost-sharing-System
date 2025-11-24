package com.example.EV.Co_ownership.Cost_sharing.system.Service;

import com.example.EV.Co_ownership.Cost_sharing.system.Util.VnPayUtil;
import com.example.EV.Co_ownership.Cost_sharing.system.config.VnPayConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
public class VnPayService {

    private final VnPayConfig vnPayConfig;

    public String createPaymentUrl(String orderId, long amount, String content) throws Exception {

        String vnp_TxnRef = orderId;
        String vnp_IpAddr = "127.0.0.1";

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        String vnp_CreateDate = formatter.format(cld.getTime());

        Map<String, String> params = new HashMap<>();
        params.put("vnp_Version", "2.1.0");
        params.put("vnp_Command", "pay");
        params.put("vnp_TmnCode", vnPayConfig.getTmnCode());
        params.put("vnp_Amount", String.valueOf(amount * 100));
        params.put("vnp_CurrCode", "VND");
        params.put("vnp_TxnRef", vnp_TxnRef);
        params.put("vnp_OrderInfo", content);
        params.put("vnp_OrderType", "other");
        params.put("vnp_Locale", "vn");
        params.put("vnp_IpAddr", vnp_IpAddr);
        params.put("vnp_ReturnUrl", vnPayConfig.getReturnUrl());
        params.put("vnp_CreateDate", vnp_CreateDate);

        return VnPayUtil.buildPaymentUrl(params, vnPayConfig.getHashSecret(), vnPayConfig.getPayUrl());
    }
}
