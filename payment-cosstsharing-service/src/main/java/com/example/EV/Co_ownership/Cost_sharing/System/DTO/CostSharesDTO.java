package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import java.math.BigDecimal;

public class CostSharesDTO {
    private int cost_id;
    private int vehicle_id;
    private int user_id;
    private BigDecimal share_percentage;
    private BigDecimal amount_due;
    public BigDecimal settle_amount;

    public int getCost_id() {
        return cost_id;
    }

    public void setCost_id(int cost_id) {
        this.cost_id = cost_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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

    public BigDecimal getSettle_amount() {
        return settle_amount;
    }

    public void setSettle_amount(BigDecimal settle_amount) {
        this.settle_amount = settle_amount;
    }
}
