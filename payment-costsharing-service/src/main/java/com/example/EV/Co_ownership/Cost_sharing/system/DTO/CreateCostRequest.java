package com.example.EV.Co_ownership.Cost_sharing.system.DTO;

import java.math.BigDecimal;

public record CreateCostRequest(
        int groupId,
        Integer fundId,
        int vehicleId,
        String costName,
        BigDecimal amount
) {}