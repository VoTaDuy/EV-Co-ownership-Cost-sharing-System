// DTO/FundTransactionDTO.java
package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

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
        String performedBy,
        String gatewayOrderId,
        String status,
        LocalDateTime createdAt
) {}