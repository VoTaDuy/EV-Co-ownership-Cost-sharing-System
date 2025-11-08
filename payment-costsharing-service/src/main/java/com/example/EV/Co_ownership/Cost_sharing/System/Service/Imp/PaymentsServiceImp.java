package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;


import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Payments;

import java.util.List;
import java.util.Optional;

public interface PaymentsServiceImp {
    Payments savePayment(Payments payment);
    List<Payments> getAllPayments();
    Optional<Payments> getPaymentById(int paymentId);
    void deletePayment(int paymentId);
    List<Payments> getPaymentsByGroupId(String groupId);
    List<Payments> getPaymentsByUserId(String userId);
}
