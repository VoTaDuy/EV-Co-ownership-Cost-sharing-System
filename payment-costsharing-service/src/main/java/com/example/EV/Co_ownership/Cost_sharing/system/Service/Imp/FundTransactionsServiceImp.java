package com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.FundTransactionDTO;

import java.util.List;

public interface FundTransactionsServiceImp {
    List<FundTransactionDTO> getByFund(Integer fundId);
    List<FundTransactionDTO> getByCost(Integer costId);
    FundTransactionDTO getById(Integer transactionId);
    List<FundTransactionDTO> getAll();
}