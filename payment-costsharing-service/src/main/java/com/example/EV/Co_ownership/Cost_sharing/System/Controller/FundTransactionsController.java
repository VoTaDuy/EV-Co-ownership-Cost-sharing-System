package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.FundTransactionDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.FundTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment/transactions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class FundTransactionsController {

    private final FundTransactionService txService;

    @GetMapping("/fund/{fundId}")
    public List<FundTransactionDTO> getByFund(@PathVariable Integer fundId) {
        return txService.getByFund(fundId);
    }

    @GetMapping("/cost/{costId}")
    public List<FundTransactionDTO> getByCost(@PathVariable Integer costId) {
        return txService.getByCost(costId);
    }

    @GetMapping("/{id}")
    public FundTransactionDTO getById(@PathVariable Integer id) {
        return txService.getById(id);
    }

    @GetMapping("/admin/fund-transactions")
    public ResponseEntity<List<FundTransactionDTO>> getAllTransactions() {
        List<FundTransactionDTO> transactions = txService.getAll();
        return ResponseEntity.ok(transactions);
    }
}