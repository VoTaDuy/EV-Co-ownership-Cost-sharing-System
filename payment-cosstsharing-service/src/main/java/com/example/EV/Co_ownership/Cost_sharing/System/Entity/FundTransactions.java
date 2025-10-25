package com.example.EV.Co_ownership.Cost_sharing.System.Entity;


import com.example.EV.Co_ownership.Cost_sharing.System.Enum.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "fund_transactions")
public class FundTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction_id;

    @ManyToOne
    @JoinColumn(name = "fund_id")
    private GroupFund fund_id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payments payment_id;

    @ManyToOne
    @JoinColumn(name = "cost_id")
    private VehicleCost cost_id;

    @Column(name = "transaction_type")
    private TransactionType transaction_type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "performed_by")
    private String performed_by;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public GroupFund getFund_id() {
        return fund_id;
    }

    public void setFund_id(GroupFund fund_id) {
        this.fund_id = fund_id;
    }

    public Payments getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Payments payment_id) {
        this.payment_id = payment_id;
    }

    public VehicleCost getCost_id() {
        return cost_id;
    }

    public void setCost_id(VehicleCost cost_id) {
        this.cost_id = cost_id;
    }

    public TransactionType getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(TransactionType transaction_type) {
        this.transaction_type = transaction_type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPerformed_by() {
        return performed_by;
    }

    public void setPerformed_by(String performed_by) {
        this.performed_by = performed_by;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
