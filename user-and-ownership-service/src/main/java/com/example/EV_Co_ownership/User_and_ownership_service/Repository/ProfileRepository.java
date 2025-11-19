package com.example.EV_Co_ownership.User_and_ownership_service.Repository;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profiles, Integer> {

    // Lấy profile theo userId
    Optional<Profiles> findProfileByUserId(int userId);

    // Lấy tất cả profile sắp xếp theo userId giảm dần
    List<Profiles> findAllByOrderByUserIdDesc();

    // Tìm profile theo full_name (ví dụ search)
    List<Profiles> findByFullNameContainingIgnoreCase(String fullName);

    // Tìm profile theo phone_number
    Optional<Profiles> findByPhoneNumber(String phoneNumber);

    // Kiểm tra tồn tại profile theo userId
    boolean existsByUserId(int userId);
}
