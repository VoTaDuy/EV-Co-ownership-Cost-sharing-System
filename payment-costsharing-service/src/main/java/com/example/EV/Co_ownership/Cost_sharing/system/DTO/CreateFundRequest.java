package com.example.EV.Co_ownership.Cost_sharing.system.DTO;

import java.math.BigDecimal;

public record CreateFundRequest(
        String fundName,
        BigDecimal initialBalance
) {}
