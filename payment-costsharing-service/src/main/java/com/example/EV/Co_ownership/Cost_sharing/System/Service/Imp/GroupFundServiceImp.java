package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CreateFundRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.DepositRequest;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.FundTransactionDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.DTO.GroupFundDTO;

import java.util.List;

public interface GroupFundServiceImp {

    List<GroupFundDTO> getAll(int groupId);

    GroupFundDTO create(CreateFundRequest request, int userId);

    GroupFundDTO getById(Integer fundId);

    String initiateDeposit(Integer fundId, DepositRequest request, int userId);

    void handleDepositCallback(String orderId, String status, String transactionId, String response);

    void deleteFund(Integer fundId, int userId);

    List<FundTransactionDTO> getTransactions(Integer fundId);
}