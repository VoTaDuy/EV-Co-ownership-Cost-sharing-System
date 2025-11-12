package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GroupFundDTO(
        Integer fundId,
        String groupId,
        String fundName,
        BigDecimal balance,
        String createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}