package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.EV_Co_ownership.User_and_ownership_service.DTO.ProfileDTO;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Profiles;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.ProfileRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;
    public Profiles createProfile(ProfileDTO dto) {
        Profiles profile = new Profiles();
        profile.setUserId(dto.getUserId());
        profile.setFullName(dto.getFullName());
        profile.setPhoneNumber(dto.getPhoneNumber());
        profile.setAddress(dto.getAddress());
        profile.setDriverLicenseNumber(dto.getDriverLicenseNumber());
        profile.setDriverLicenseExpiry(dto.getDriverLicenseExpiry());
        profile.setLicenseImageUrl(dto.getLicenseImageUrl());

        return profileRepository.save(profile);
    }

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

            int roleId = 1; // Mặc định là User

            return new ProfileWithUserInfo(
                    profile.getUserId(),
                    email,
                    roleId,
                    profile.getFullName(),
                    profile.getPhoneNumber(),
                    profile.getAddress(),
                    profile.getDriverLicenseNumber(),
                    profile.getDriverLicenseExpiry(),
                    profile.getLicenseImageUrl()
            );
        }).collect(Collectors.toList());
    }

    public Profiles updateProfile(
            int userId,
            String fullName,
            String phoneNumber,
            String address,
            String driverLicenseNumber,
            String driverLicenseExpiry,
            MultipartFile licenseFile
    ) throws Exception {
        Profiles profile = getProfileByUserId(userId);

        if (fullName != null) profile.setFullName(fullName);
        if (phoneNumber != null) profile.setPhoneNumber(phoneNumber);
        if (address != null) profile.setAddress(address);
        if (driverLicenseNumber != null) profile.setDriverLicenseNumber(driverLicenseNumber);
        if (driverLicenseExpiry != null) profile.setDriverLicenseExpiry(driverLicenseExpiry);

        if (licenseFile != null && !licenseFile.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(licenseFile.getBytes(),
                    ObjectUtils.asMap("folder", "licenses"));
            profile.setLicenseImageUrl(uploadResult.get("secure_url").toString());
        }

        return profileRepository.save(profile);
    }

    public void deleteProfile(int userId) {
        Profiles profile = getProfileByUserId(userId);
        profileRepository.delete(profile);
    }

    public record ProfileWithUserInfo(
            int userId,
            String email,
            int roleId,
            String fullName,
            String phoneNumber,
            String address,
            String driverLicenseNumber,
            String driverLicenseExpiry,
            String licenseImageUrl
    ) {}
}
