package com.example.EV_Co_ownership.User_and_ownership_service.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "profiles")
public class Profiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "full_name")
    private String fullName;  // ✅ đổi sang camelCase

    @Column(name = "phone_number")
    private String phoneNumber;  // ✅ camelCase

    @Column(name = "address")
    private String address;

    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "driver_license_expiry")
    private String driverLicenseExpiry;

    @Column(name = "license_image_url")
    private String licenseImageUrl;
}
