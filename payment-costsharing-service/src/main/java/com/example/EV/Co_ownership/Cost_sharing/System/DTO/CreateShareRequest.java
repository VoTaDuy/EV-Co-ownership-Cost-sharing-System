package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import java.math.BigDecimal;
import java.util.List;

public record CreateShareRequest(
        int costId,
        List<ShareDetail> shares
) {
    public record ShareDetail(int userId, BigDecimal percentage) {}
}