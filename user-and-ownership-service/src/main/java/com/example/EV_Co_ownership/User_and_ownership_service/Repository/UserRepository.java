package com.example.EV_Co_ownership.User_and_ownership_service.Repository;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findByUserIdAndIsDeletedFalse(int id);

    Optional<Users> findByPasswordResetToken(String token);

    @Query("SELECT u FROM Users u WHERE u.isDeleted = false")
    List<Users> findByIsDeletedFalse();
}