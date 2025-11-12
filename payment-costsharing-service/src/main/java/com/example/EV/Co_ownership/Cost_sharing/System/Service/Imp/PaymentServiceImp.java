package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PaymentDTO;

import java.util.List;
import java.util.Map;

public interface PaymentServiceImp {
    List<PaymentDTO> getByGroup(String groupId);
    List<PaymentDTO> getByUser(String userId);
    List<PaymentDTO> getByCost(Integer costId);
    List<PaymentDTO> getByFund(Integer fundId);
    PaymentDTO createPayment(Map<String, Object> request, String userId);
    PaymentDTO handleGatewayCallback(String gatewayOrderId, String status, String response);
}