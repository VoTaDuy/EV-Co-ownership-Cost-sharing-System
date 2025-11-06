package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Roles;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request.RegisterRequest;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public Users checkLogin(String email, String password) {
        Users user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("User logged in");
            return user;
        } else {
            System.out.println("Wrong password");
            return null;
        }
    }

    public boolean registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        Users user = new Users();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Roles role = new Roles();
        role.setRole_id(registerRequest.getRole_id());
        user.setRoles(role);
        user.setIs_verified(false);
        user.setCreated_at(LocalDateTime.now());
        user.setDeleted(false);
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void handleForgotPassword(String email) {

        Users user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            System.out.println("Yêu cầu reset cho email không tồn tại: " + email);
            return;
        }

        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);

        user.setPasswordResetToken(token);
        user.setResetTokenExpiry(expiryDate);

        userRepository.save(user);

        String resetLink = "http://localhost:3000/reset-password?token=" + token;
        String emailBody = "Xin chào " + user.getEmail() + ",\n\n"
                + "Bạn đã yêu cầu đặt lại mật khẩu...\n"
                + resetLink + "\n\n"
                + "Đội ngũ ECarSharing";

        emailService.sendSimpleMessage(
                user.getEmail(),
                "ECarSharing - Yêu cầu Đặt lại Mật khẩu",
                emailBody
        );
    }
}