package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.Paymentstatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "user_id")
    private String user_id;

    @ManyToOne
    @JoinColumn(name = "cost_id")
    private VehicleCost cost_id;

    @ManyToOne
    @JoinColumn(name = "fund_id")
    private GroupFund fund_id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_date")
    private LocalDateTime payment_date;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "status")
    private Paymentstatus  status;

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public VehicleCost getCost_id() {
        return cost_id;
    }

    public void setCost_id(VehicleCost cost_id) {
        this.cost_id = cost_id;
    }

    public GroupFund getFund_id() {
        return fund_id;
    }

    public void setFund_id(GroupFund fund_id) {
        this.fund_id = fund_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Paymentstatus getStatus() {
        return status;
    }

    public void setStatus(Paymentstatus status) {
        this.status = status;
    }
}
