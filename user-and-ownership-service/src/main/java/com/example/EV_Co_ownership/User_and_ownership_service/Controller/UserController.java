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

@RestController
@RequestMapping("/user/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    @Autowired private UserService userService;
    @Autowired private ProfileService profileService;

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

    @GetMapping("/admin/profiles")
    public ResponseEntity<?> getAllProfilesForAdmin() {
        try {
            return ResponseEntity.ok(profileService.getAllProfilesWithUserInfo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi server: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, userDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete_user")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        try {
            Users user = userService.removeUserById(id);
            responseData.setData(user);
            responseData.setMessage("Xóa user thành công");
            return ResponseEntity.ok(responseData);
        } catch (EntityNotFoundException e) {
            responseData.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        } catch (Exception e) {
            responseData.setMessage("Lỗi server: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<?> getProfileByUserId(@PathVariable int userId) {
        try {
            Profiles profile = profileService.getProfileByUserId(userId);
            return ResponseEntity.ok(profile);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable int userId, @RequestBody ProfileDTO profileDTO) {
        try {
            Profiles updated = profileService.updateProfile(userId, profileDTO);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}