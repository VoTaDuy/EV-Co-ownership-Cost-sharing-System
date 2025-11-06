package com.example.EV_Co_ownership.User_and_ownership_service.Repository;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findByToken(String token);
}