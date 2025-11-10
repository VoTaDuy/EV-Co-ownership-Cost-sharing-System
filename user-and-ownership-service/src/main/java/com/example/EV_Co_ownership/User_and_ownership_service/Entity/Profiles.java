package com.example.EV_Co_ownership.User_and_ownership_service.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "profiles")
public class Profiles {

    @Id
    @GeneratedValue
    @Column(name = "profiles_id", columnDefinition = "BINARY(16)")
    private UUID profiles_id;

    @Column(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID user_id;

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
}
