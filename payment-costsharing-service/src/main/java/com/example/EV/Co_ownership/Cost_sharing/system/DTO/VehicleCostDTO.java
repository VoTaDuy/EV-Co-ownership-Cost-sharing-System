package com.example.EV.Co_ownership.Cost_sharing.system.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VehicleCostDTO(
        Integer costId,
        int groupId,
        int userId,
        int vehicleId,
        String costName,
        BigDecimal amount,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}