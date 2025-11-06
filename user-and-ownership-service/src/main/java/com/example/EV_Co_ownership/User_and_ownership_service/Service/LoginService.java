package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.PasswordResetToken;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.PasswordResetTokenRepository;
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
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    public Users checkLogin(String email, String password) {
        Users user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }
        System.out.println("Input email: " + email);
        System.out.println("Input password: " + password);

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
        System.out.println("Input email: " + registerRequest.getEmail());
        Roles role = new Roles();
        role.setRole_id(registerRequest.getRole_id());
        user.setRoles(role);
        user.setIs_verified(false);
        user.setCreated_at(LocalDateTime.now());
        user.setDeleted(false);

        try {
            System.out.println("Inserting user");
            userRepository.save(user);
            System.out.println("Inserted user");
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

        PasswordResetToken resetToken = new PasswordResetToken(token, user, expiryDate);
        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:3000/reset-password?token=" + token;

        String emailBody = "Xin chào " + user.getEmail() + ",\n\n"
                + "Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản ECarSharing.\n"
                + "Vui lòng nhấp vào liên kết dưới đây để đặt lại mật khẩu của bạn:\n"
                + resetLink + "\n\n"
                + "Nếu bạn không yêu cầu điều này, vui lòng bỏ qua email này.\n\n"
                + "Trân trọng,\n"
                + "Đội ngũ ECarSharing";

        emailService.sendSimpleMessage(
                user.getEmail(),
                "ECarSharing - Yêu cầu Đặt lại Mật khẩu",
                emailBody
        );
    }
}