package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.FundTransactions;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.FundTransactionsRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.FundTransactionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FundTransactionsService implements FundTransactionsServiceImp {
    @Autowired
    private FundTransactionsRepository fundTransactionsRepository;

    @Override
    public FundTransactions createTransaction(FundTransactions transaction) {
        return fundTransactionsRepository.save(transaction);
    }

    @Override
    public FundTransactions updateTransaction(FundTransactions transaction) {
        if (fundTransactionsRepository.existsById(transaction.getTransaction_id())) {
            return fundTransactionsRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    @Override
    public void deleteTransaction(int transactionId) {
        if (fundTransactionsRepository.existsById(transactionId)) {
            fundTransactionsRepository.deleteById(transactionId);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    @Override
    public Optional<FundTransactions> getTransactionById(int transactionId) {
        return fundTransactionsRepository.findById(transactionId);
    }

    @Override
    public List<FundTransactions> getAllTransactions() {
        return fundTransactionsRepository.findAll();
    }
}

