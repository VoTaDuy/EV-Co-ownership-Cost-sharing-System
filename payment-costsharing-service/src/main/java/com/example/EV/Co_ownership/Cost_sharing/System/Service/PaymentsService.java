package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Payments;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PaymentsRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PaymentsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentsService implements PaymentsServiceImp {
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Override
    public Payments savePayment(Payments payment) {
        return paymentsRepository.save(payment);
    }

    @Override
    public List<Payments> getAllPayments() {
        return paymentsRepository.findAll();
    }

    @Override
    public Optional<Payments> getPaymentById(int paymentId) {
        return paymentsRepository.findById(paymentId);
    }

    @Override
    public void deletePayment(int paymentId) {
        paymentsRepository.deleteById(paymentId);
    }

    @Override
    public List<Payments> getPaymentsByGroupId(String groupId) {
        return paymentsRepository.findByGroupId(groupId);
    }

    @Override
    public List<Payments> getPaymentsByUserId(String userId) {
        return paymentsRepository.findByUser_id(userId);
    }
}
