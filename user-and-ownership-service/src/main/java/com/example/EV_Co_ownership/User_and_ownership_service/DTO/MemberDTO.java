package com.example.EV_Co_ownership.User_and_ownership_service.DTO;

import java.math.BigDecimal;

public class MemberDTO {

    private Integer userId;
    private String userName; // hoặc email nếu muốn
    private BigDecimal ownershipRatio;

    public MemberDTO() {}

    public MemberDTO(Integer userId, String userName, BigDecimal ownershipRatio) {
        this.userId = userId;
        this.userName = userName;
        this.ownershipRatio = ownershipRatio;
    }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public BigDecimal getOwnershipRatio() { return ownershipRatio; }

    public void setOwnershipRatio(BigDecimal ownershipRatio) { this.ownershipRatio = ownershipRatio; }
}
