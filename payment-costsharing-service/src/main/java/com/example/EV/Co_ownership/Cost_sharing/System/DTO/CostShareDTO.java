package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostShareDTO {
    private Integer shareId;
    private Integer costId;
    private int userId;
    private BigDecimal sharePercentage;
    private BigDecimal amountDue;
    private BigDecimal settledAmount;
    private String status;
    private LocalDateTime createdAt;
}