package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.DTO.ProfileDTO;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Profiles;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.ProfileRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Profiles> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Profiles getProfileByUserId(int userId) {
        return profileRepository.findProfileByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user: " + userId));
    }

    public List<ProfileWithUserInfo> getAllProfilesWithUserInfo() {
        List<Profiles> profiles = profileRepository.findAll();

        return profiles.stream().map(profile -> {
            Users user = userRepository.findById(profile.getUserId()).orElse(null);

            String email = user != null ? user.getEmail() : "unknown@example.com";

            int role_id = 1;
            if (user != null && user.getRoles() != null) {
                role_id = user.getRoles().getRole_id();
            }

            return new ProfileWithUserInfo(
                    profile.getUserId(),
                    email,
                    role_id,
                    profile.getFull_name(),
                    profile.getPhone_number(),
                    profile.getAddress(),
                    profile.getDriver_license_number(),
                    profile.getDriver_license_expiry(),
                    profile.getLicense_image_url()
            );
        }).collect(Collectors.toList());
    }

    public Profiles updateProfile(int userId, ProfileDTO profileDTO) {
        Profiles profile = getProfileByUserId(userId);

        if (profileDTO.getFull_name() != null)
            profile.setFull_name(profileDTO.getFull_name());
        if (profileDTO.getPhone_number() != null)
            profile.setPhone_number(profileDTO.getPhone_number());
        if (profileDTO.getAddress() != null)
            profile.setAddress(profileDTO.getAddress());
        if (profileDTO.getDriver_license_number() != null)
            profile.setDriver_license_number(profileDTO.getDriver_license_number());
        if (profileDTO.getDriver_license_expiry() != null)
            profile.setDriver_license_expiry(profileDTO.getDriver_license_expiry());
        if (profileDTO.getLicense_image_url() != null)
            profile.setLicense_image_url(profileDTO.getLicense_image_url());

        return profileRepository.save(profile);
    }

    public void deleteProfile(int userId) {
        Profiles profile = getProfileByUserId(userId);
        profileRepository.delete(profile);
    }

    public record ProfileWithUserInfo(
            int userId,
            String email,
            int role_id,
            String full_name,
            String phone_number,
            String address,
            String driver_license_number,
            String driver_license_expiry,
            String license_image_url
    ) {}
}