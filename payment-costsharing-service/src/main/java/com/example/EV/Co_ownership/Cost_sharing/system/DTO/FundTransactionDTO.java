// DTO/FundTransactionDTO.java
package com.example.EV.Co_ownership.Cost_sharing.system.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FundTransactionDTO(
        Integer transactionId,
        Integer fundId,
        Integer paymentId,      // MỚI
        Integer costId,         // MỚI
        String transactionType,
        BigDecimal amount,
        String description,
        int performedBy,
        String gatewayOrderId,
        String status,
        LocalDateTime createdAt
) {}