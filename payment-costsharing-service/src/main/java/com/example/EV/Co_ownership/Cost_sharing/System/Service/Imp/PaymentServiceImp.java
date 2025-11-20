package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PaymentDTO;

import java.util.List;
import java.util.Map;

public interface PaymentServiceImp {
    List<PaymentDTO> getAllPayments();

    List<PaymentDTO> getByGroup(int groupId);
    List<PaymentDTO> getByUser(int userId);
    List<PaymentDTO> getByCost(Integer costId);
    List<PaymentDTO> getByFund(Integer fundId);
    PaymentDTO createPayment(Map<String, Object> request, int userId);
    PaymentDTO handleGatewayCallback(String gatewayOrderId, String status, String response);

    PaymentDTO handleFakePaymentSuccess(String gatewayOrderId);
}