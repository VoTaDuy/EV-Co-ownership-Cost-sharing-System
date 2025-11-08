package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.FundTransactions;

import java.util.List;
import java.util.Optional;

public interface FundTransactionsServiceImp {
    FundTransactions createTransaction(FundTransactions transaction);
    FundTransactions updateTransaction(FundTransactions transaction);
    void deleteTransaction(int transactionId);
    Optional<FundTransactions> getTransactionById(int transactionId);
    List<FundTransactions> getAllTransactions();
}
