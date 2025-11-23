package com.example.EV_Co_ownership.User_and_ownership_service.Controller;

import com.example.EV_Co_ownership.User_and_ownership_service.DTO.ProfileDTO;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Profiles;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/profiles")
    public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/admin")
    public ResponseEntity<?> getAllProfilesForAdmin() {
        try {
            return ResponseEntity.ok(profileService.getAllProfilesWithUserInfo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi server: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable int userId) {
        try {
            Profiles profile = profileService.getProfileByUserId(userId);
            return ResponseEntity.ok(profile);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateProfile(
            @PathVariable int userId,
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "driverLicenseNumber", required = false) String driverLicenseNumber,
            @RequestParam(value = "driverLicenseExpiry", required = false) String driverLicenseExpiry,
            @RequestParam(value = "licenseFile", required = false) MultipartFile licenseFile
    ) {
        try {
            Profiles updated = profileService.updateProfile(
                    userId,
                    fullName,
                    phoneNumber,
                    address,
                    driverLicenseNumber,
                    driverLicenseExpiry,
                    licenseFile
            );
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/{userId}")
    public ResponseEntity<?> createProfile(@PathVariable int userId, @RequestBody ProfileDTO dto) {
        try {
            dto.setUserId(userId);
            Profiles profile = profileService.createProfile(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(profile);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
