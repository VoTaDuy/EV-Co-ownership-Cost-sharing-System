package com.example.EV.Co_ownership.Cost_sharing.system.DTO;

import java.math.BigDecimal;

public record CreateFundRequest(
        int groupId,
        String fundName,
        BigDecimal initialBalance
) {}
