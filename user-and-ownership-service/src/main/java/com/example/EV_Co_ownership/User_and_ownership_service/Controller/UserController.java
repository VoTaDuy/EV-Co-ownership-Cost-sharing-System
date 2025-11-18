package com.example.EV_Co_ownership.User_and_ownership_service.Controller;

import com.example.EV_Co_ownership.User_and_ownership_service.DTO.ProfileDTO;
import com.example.EV_Co_ownership.User_and_ownership_service.DTO.UserDTO;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Profiles;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.ResponseData;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.ProfileService;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok()
                .cacheControl(org.springframework.http.CacheControl.noStore())
                .header("Pragma", "no-cache")
                .body(userService.getAllUsers());
    }

    @GetMapping("/crsf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/profiles")
    public ResponseEntity<?> getAllProfiles() {
        try {
            List<Profiles> profiles = profileService.getAllProfiles();
            return new ResponseEntity<>(profiles != null ? profiles : List.of(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi server nội bộ: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            Users user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        try {
            Users updatedUser = userService.updateUser(id, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/delete_user")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        try {
            Users user = userService.removeUserById(id);
            responseData.setData(user);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            responseData.setMessage(e.getMessage());
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseData.setMessage("Lỗi server: " + e.getMessage());
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<?> getProfileByUserId(@PathVariable int userId) {
        try {
            Profiles profile = profileService.getProfileByUserId(userId);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable int userId, @RequestBody ProfileDTO profileDTO) {
        try {
            Profiles updatedProfile = profileService.updateProfile(userId, profileDTO);
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/profiles")
    public ResponseEntity<?> getAllProfilesForAdmin() {
        try {
            List<ProfileService.ProfileWithUserInfo> profiles = profileService.getAllProfilesWithUserInfo();
            return ResponseEntity.ok(profiles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi server: " + e.getMessage());
        }
    }

    @DeleteMapping("/admin/profiles/{userId}")
    public ResponseEntity<?> deleteProfileByAdmin(@PathVariable int userId) {
        try {
            profileService.deleteProfile(userId);
            return ResponseEntity.ok("Xóa hồ sơ thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}