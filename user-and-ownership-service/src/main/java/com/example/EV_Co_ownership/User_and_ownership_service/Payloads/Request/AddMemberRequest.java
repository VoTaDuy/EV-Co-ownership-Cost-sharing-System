package com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request;

import java.math.BigDecimal;

public class AddMemberRequest {

    private int groupId;
    private String userEmail;
    private BigDecimal ownershipRatio;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public BigDecimal getOwnershipRatio() {
        return ownershipRatio;
    }

    public void setOwnershipRatio(BigDecimal ownershipRatio) {
        this.ownershipRatio = ownershipRatio;
    }
}