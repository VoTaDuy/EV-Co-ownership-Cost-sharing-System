package com.example.EV.Co_ownership.Cost_sharing.system.Repository;

import com.example.EV.Co_ownership.Cost_sharing.system.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByGatewayOrderId(String gatewayOrderId);
    List<Payment> findByGroupId(int groupId);
    List<Payment> findByUserId(int userId);
    List<Payment> findByCost_CostId(Integer costId);
    List<Payment> findByFund_FundId(Integer fundId);
}