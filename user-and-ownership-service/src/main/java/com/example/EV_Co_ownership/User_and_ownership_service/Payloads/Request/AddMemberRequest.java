package com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request;

import java.math.BigDecimal;

public class AddMemberRequest {

    private int groupId;
    private int userId;
    private BigDecimal ownershipRatio;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getOwnershipRatio() {
        return ownershipRatio;
    }

    public void setOwnershipRatio(BigDecimal ownershipRatio) {
        this.ownershipRatio = ownershipRatio;
    }
}