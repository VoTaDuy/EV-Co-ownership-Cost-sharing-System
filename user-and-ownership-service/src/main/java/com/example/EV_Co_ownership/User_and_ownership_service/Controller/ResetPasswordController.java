// src/main/java/com/example/EV_Co_ownership/User_and_ownership_service/Controller/ResetPasswordController.java

package com.example.EV_Co_ownership.User_and_ownership_service.Controller;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class ResetPasswordController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String newPassword = body.get("newPassword");

        if (token == null || newPassword == null || newPassword.isEmpty()) {
            return ResponseEntity.badRequest().body("Dữ liệu không hợp lệ");
        }

        // Tìm user có token này
        Users user = userRepository.findByPasswordResetToken(token)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body("Liên kết không hợp lệ hoặc đã được sử dụng");
        }

        // Kiểm tra token có hết hạn không
        if (user.getResetTokenExpiry() == null ||
                user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Liên kết đã hết hạn");
        }

        // Đổi mật khẩu + xóa token
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);

        return ResponseEntity.ok("Đặt lại mật khẩu thành công!");
    }
}