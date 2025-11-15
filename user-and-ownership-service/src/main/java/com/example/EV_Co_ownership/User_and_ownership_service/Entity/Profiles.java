package com.example.EV_Co_ownership.User_and_ownership_service.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Entity
@Table(name = "profiles")
public class Profiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "address")
    private String address;

    @Column(name = "driver_license_number")
    private String driver_license_number;

    @Column(name = "driver_license_expiry")
    private String driver_license_expiry;

    @Column(name = "license_image_url")
    private String license_image_url;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriver_license_number() {
        return driver_license_number;
    }

    public void setDriver_license_number(String driver_license_number) {
        this.driver_license_number = driver_license_number;
    }

    public String getDriver_license_expiry() {
        return driver_license_expiry;
    }

    public void setDriver_license_expiry(String driver_license_expiry) {
        this.driver_license_expiry = driver_license_expiry;
    }

    public String getLicense_image_url() {
        return license_image_url;
    }

    public void setLicense_image_url(String license_image_url) {
        this.license_image_url = license_image_url;
    }
}
