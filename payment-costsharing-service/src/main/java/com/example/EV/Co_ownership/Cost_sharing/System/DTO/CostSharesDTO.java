package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.CostShareStatus;

import java.math.BigDecimal;

public class CostSharesDTO {
    private int share_id;
    private int cost_id;
    private String user_id;
    private BigDecimal share_percentage;
    private BigDecimal amount_due;
    private BigDecimal settled_amount;
    private CostShareStatus status;

    public int getShare_id() {
        return share_id;
    }

    public void setShare_id(int share_id) {
        this.share_id = share_id;
    }

    public int getCost_id() {
        return cost_id;
    }

    public void setCost_id(int cost_id) {
        this.cost_id = cost_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getShare_percentage() {
        return share_percentage;
    }

    public void setShare_percentage(BigDecimal share_percentage) {
        this.share_percentage = share_percentage;
    }

    public BigDecimal getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(BigDecimal amount_due) {
        this.amount_due = amount_due;
    }

    public BigDecimal getSettled_amount() {
        return settled_amount;
    }

    public void setSettled_amount(BigDecimal settled_amount) {
        this.settled_amount = settled_amount;
    }

    public CostShareStatus getStatus() {
        return status;
    }

    public void setStatus(CostShareStatus status) {
        this.status = status;
    }
}