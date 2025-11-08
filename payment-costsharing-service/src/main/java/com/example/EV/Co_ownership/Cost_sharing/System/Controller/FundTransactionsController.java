package com.example.EV.Co_ownership.Cost_sharing.System.Controller;


import com.example.EV.Co_ownership.Cost_sharing.System.DTO.FundTransactionsDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.FundTransactions;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.TransactionType;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.GroupFundRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PaymentsRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.FundTransactionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fund-transactions")
@CrossOrigin(origins = "http://localhost:3000")
public class FundTransactionsController {
    @Autowired
    private FundTransactionsServiceImp fundTransactionsServiceImp;

    @Autowired
    private GroupFundRepository groupFundRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private VehicleCostRepository vehicleCostRepository;


    @PostMapping("/create")
    public ResponseEntity<FundTransactions> createTransaction(@RequestBody FundTransactionsDTO dto) {

        FundTransactions transaction = new FundTransactions();

        transaction.setFund_id(groupFundRepository.findById(dto.getFund_id())
                .orElseThrow(() -> new RuntimeException("Fund not found")));

        if(dto.getPayment_id() > 0) // nếu có payment
            transaction.setPayment_id(paymentsRepository.findById(dto.getPayment_id()).orElse(null));

        if(dto.getCost_id() > 0) // nếu có cost
            transaction.setCost_id(vehicleCostRepository.findById(dto.getCost_id()).orElse(null));

        transaction.setTransaction_type(dto.getTransaction_type());
        transaction.setAmount(dto.getAmount());
        transaction.setDescription(dto.getDescription());
        transaction.setPerformed_by(dto.getPerformed_by());
        transaction.setCreated_at(dto.getCreated_at());

        FundTransactions created = fundTransactionsServiceImp.createTransaction(transaction);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }



    @PutMapping("/update")
    public ResponseEntity<FundTransactions> updateTransaction(@RequestBody FundTransactions transaction) {
        FundTransactions updated = fundTransactionsServiceImp.updateTransaction(transaction);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int id) {
        fundTransactionsServiceImp.deleteTransaction(id);
        return ResponseEntity.ok("Transaction deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<FundTransactions> getTransactionById(@PathVariable int id) {
        return fundTransactionsServiceImp.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<FundTransactions>> getAllTransactions() {
        List<FundTransactions> list = fundTransactionsServiceImp.getAllTransactions();
        return ResponseEntity.ok(list);
    }
}
