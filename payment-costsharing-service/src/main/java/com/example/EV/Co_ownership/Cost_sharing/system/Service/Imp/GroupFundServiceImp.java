package com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.CreateFundRequest;
import com.example.EV.Co_ownership.Cost_sharing.system.DTO.DepositRequest;
import com.example.EV.Co_ownership.Cost_sharing.system.DTO.FundTransactionDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.DTO.GroupFundDTO;

import java.util.List;

public interface GroupFundServiceImp {

    List<GroupFundDTO> getAll(int groupId);

    GroupFundDTO create(CreateFundRequest request, int userId, int groupId);

    GroupFundDTO getById(Integer fundId);

    String initiateDeposit(Integer fundId, DepositRequest request, int userId);

    void handleDepositCallback(String orderId, String status, String transactionId, String response);

    void deleteFund(Integer fundId, int userId);

    List<FundTransactionDTO> getTransactions(Integer fundId);
}