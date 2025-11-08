package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

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
                + "Bạn đã yêu cầu đặt lại mật khẩu.\n"
                + "Hãy truy cập liên kết sau để đặt lại mật khẩu:\n"
                + resetLink + "\n\n"
                + "Đội ngũ ECarSharing";

        emailService.sendSimpleMessage(
                user.getEmail(),
                "ECarSharing - Yêu cầu Đặt lại Mật khẩu",
                emailBody
        );
    }
}
