package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.FundTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundTransactionsRepository extends JpaRepository<FundTransactions, Integer> {

}
