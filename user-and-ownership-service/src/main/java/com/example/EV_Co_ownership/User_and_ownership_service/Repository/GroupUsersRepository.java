package com.example.EV_Co_ownership.User_and_ownership_service.Repository;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUsersRepository extends JpaRepository<Groups, Integer> {
    // JpaRepository đã cung cấp đủ các hàm (như .save(), .findById())
    // Chúng ta chưa cần thêm hàm tùy chỉnh
}