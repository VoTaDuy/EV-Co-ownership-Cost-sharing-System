package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CreateFundRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.DepositRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.FundTransactionDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.GroupFundDTO;

import java.util.List;

public interface GroupFundServiceImp {

    List<GroupFundDTO> getAll(String groupId);

    GroupFundDTO create(CreateFundRequest request, String userId);
    GroupFundDTO getById(Integer fundId);
    String initiateDeposit(Integer fundId, DepositRequest request, String userId);
    void handleDepositCallback(String orderId, String status, String transactionId, String response);

    void deleteFund(Integer fundId, String userId);

    List<FundTransactionDTO> getTransactions(Integer id);
}