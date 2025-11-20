package com.example.EV_Co_ownership.User_and_ownership_service.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileDTO {
    private int userId;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String driverLicenseNumber;
    private String driverLicenseExpiry;
    private String licenseImageUrl;
}
