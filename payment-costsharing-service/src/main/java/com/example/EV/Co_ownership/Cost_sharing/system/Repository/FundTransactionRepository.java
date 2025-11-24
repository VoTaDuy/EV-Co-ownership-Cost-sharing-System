package com.example.EV.Co_ownership.Cost_sharing.system.Repository;

import com.example.EV.Co_ownership.Cost_sharing.system.Entity.FundTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FundTransactionRepository extends JpaRepository<FundTransaction, Integer> {
    List<FundTransaction> findByFund_FundId(Integer fundId);
    Optional<FundTransaction> findByGatewayOrderId(String gatewayOrderId);
    List<FundTransaction> findByCost_CostId(Integer costId);
}