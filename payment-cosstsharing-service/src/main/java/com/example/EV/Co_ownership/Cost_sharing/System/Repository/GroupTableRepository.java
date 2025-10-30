package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupTableRepository extends JpaRepository<GroupTable, Integer> {
}
