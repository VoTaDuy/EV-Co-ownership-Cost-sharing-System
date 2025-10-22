package com.example.EV_Co_ownership.User_and_ownership_service.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "e_contract")

public class E_contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contract_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "vehicle_id")
    private int vehicle_id;

    @Column(name = "signatrue_status")
    private String signatrue_status;

    @Column(name = "sign_at")
    private String sign_at;

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getSignatrue_status() {
        return signatrue_status;
    }

    public void setSignatrue_status(String signatrue_status) {
        this.signatrue_status = signatrue_status;
    }

    public String getSign_at() {
        return sign_at;
    }

    public void setSign_at(String sign_at) {
        this.sign_at = sign_at;
    }
}
