package com.example.EV.Co_ownership.Cost_sharing.system.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GroupFundDTO(
        Integer fundId,
        int groupId,
        String fundName,
        BigDecimal balance,
        int createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}