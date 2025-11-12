package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VehicleCostDTO(
        Integer costId,
        String groupId,
        Integer fundId,
        String vehicleId,
        String costName,
        BigDecimal amount,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}