package com.example.EV.Co_ownership.Cost_sharing.system.DTO;

import java.math.BigDecimal;
import java.util.List;

public record CreateShareRequest(
        int costId,
        List<ShareDetail> shares
) {
    public record ShareDetail(int userId, BigDecimal percentage) {}
}