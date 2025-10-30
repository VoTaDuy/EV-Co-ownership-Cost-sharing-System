package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "group_fund")
public class GroupFund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fund_id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupTable group_id;

    @Column(name = "fund_name")
    private String fund_name;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "created_by")
    private int created_by;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    public int getFund_id() {
        return fund_id;
    }

    public void setFund_id(int fund_id) {
        this.fund_id = fund_id;
    }

    public GroupTable getGroup_id() {
        return group_id;
    }

    public void setGroup_id(GroupTable group_id) {
        this.group_id = group_id;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
