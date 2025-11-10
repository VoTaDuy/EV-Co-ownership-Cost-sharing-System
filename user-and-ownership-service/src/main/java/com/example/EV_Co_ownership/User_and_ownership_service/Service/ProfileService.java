package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.DTO.ProfileDTO;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Profiles;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    // Lấy profile theo user UUID
    public Profiles getProfileByUserId(UUID userId) {
        return profileRepository.findProfileByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user: " + userId));
    }

    // Cập nhật profile
    public Profiles updateProfile(UUID userId, ProfileDTO profileDTO) {
        Profiles profile = getProfileByUserId(userId);

        if (profileDTO.getFull_name() != null) profile.setFull_name(profileDTO.getFull_name());
        if (profileDTO.getPhone_number() != null) profile.setPhone_number(profileDTO.getPhone_number());
        if (profileDTO.getAddress() != null) profile.setAddress(profileDTO.getAddress());
        if (profileDTO.getDriver_license_number() != null) profile.setDriver_license_number(profileDTO.getDriver_license_number());
        if (profileDTO.getDriver_license_expiry() != null) profile.setDriver_license_expiry(profileDTO.getDriver_license_expiry());
        if (profileDTO.getLicense_image_url() != null) profile.setLicense_image_url(profileDTO.getLicense_image_url());

        return profileRepository.save(profile);
    }
}
