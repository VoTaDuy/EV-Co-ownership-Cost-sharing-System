package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.VehicleCostStatus;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity (name = "vehicle_cost")
public class VehicleCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cost_id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private UserGroup group_id;

    @Column(name = "vehicle_id")
    private int vehicle_id;

    @Column(name = "cost_name")
    private String cost_name;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column (name = "status")
    private VehicleCostStatus status;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    public int getCost_id() {
        return cost_id;
    }

    public void setCost_id(int cost_id) {
        this.cost_id = cost_id;
    }

    public UserGroup getGroup_id() {
        return group_id;
    }

    public void setGroup_id(UserGroup group_id) {
        this.group_id = group_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getCost_name() {
        return cost_name;
    }

    public void setCost_name(String cost_name) {
        this.cost_name = cost_name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public VehicleCostStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleCostStatus status) {
        this.status = status;
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
