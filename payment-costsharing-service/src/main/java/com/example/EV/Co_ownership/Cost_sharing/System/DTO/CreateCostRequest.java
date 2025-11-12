package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import java.math.BigDecimal;

public record CreateCostRequest(
        String groupId,
        Integer fundId,
        String vehicleId,
        String costName,
        BigDecimal amount
) {}