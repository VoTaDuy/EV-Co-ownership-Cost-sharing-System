package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import java.math.BigDecimal;

public record CreateFundRequest(
        String groupId,
        String fundName,
        BigDecimal initialBalance
) {}
