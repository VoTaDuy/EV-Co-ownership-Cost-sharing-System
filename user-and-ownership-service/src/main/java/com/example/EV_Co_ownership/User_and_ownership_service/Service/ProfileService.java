package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.DTO.ProfileDTO;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Profiles;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profiles getProfileByUserId(int userId) {
        return profileRepository.findProfileByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user: " + userId));
    }

    public Profiles updateProfile(int userId, ProfileDTO profileDTO) {
        Profiles profile = getProfileByUserId(userId);

        profile.setProfiles_id(profileDTO.getProfiles_id());
        profile.setUsers_id(profileDTO.getUser_id());
        profile.setFull_name(profileDTO.getFull_name());
        profile.setPhone_number(profileDTO.getPhone_number());
        profile.setAddress(profileDTO.getAddress());
        profile.setDriver_license_number(profileDTO.getDriver_license_number());
        profile.setDriver_license_expiry(profileDTO.getDriver_license_expiry());
        profile.setLicense_image_url(profileDTO.getLicense_image_url());

        return profileRepository.save(profile);
    }
}
