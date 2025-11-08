package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

    // Lấy payments theo group
    @Query("SELECT p FROM payments p WHERE p.groupId = :groupId")
    List<Payments> findByGroupId(@Param("groupId") String groupId);

    // Lấy payments theo user (cách 2, dùng @Query vì field là user_id)
    @Query("SELECT p FROM payments p WHERE p.user_id = :userId")
    List<Payments> findByUser_id(@Param("userId") String userId);
}
