package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.CostShares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CostSharesRepository extends JpaRepository<CostShares, Integer> {
    @Query("SELECT c FROM cost_shares c WHERE c.share_id = :share_id")
    CostShares getCostShareById(int share_id);
}