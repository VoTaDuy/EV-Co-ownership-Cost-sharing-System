package com.example.EV_Co_ownership.User_and_ownership_service.Repository;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.isDeleted = false")
    List<Users> findAllActive();

    @Query("SELECT u FROM Users u WHERE u.user_id = :id AND u.isDeleted = false")
    Optional<Users> findByIdAndIsDeletedFalse(UUID id);
}
