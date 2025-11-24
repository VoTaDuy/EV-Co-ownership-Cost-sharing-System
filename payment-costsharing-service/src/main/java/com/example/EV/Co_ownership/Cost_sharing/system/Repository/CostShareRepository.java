    package com.example.EV.Co_ownership.Cost_sharing.system.Repository;

    import com.example.EV.Co_ownership.Cost_sharing.system.Entity.CostShare;
    import com.example.EV.Co_ownership.Cost_sharing.system.Enum.CostShareStatus;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface CostShareRepository extends JpaRepository<CostShare, Integer> {
        List<CostShare> findByCost_CostId(Integer costId);
        List<CostShare> findByUserId(int userId);
        List<CostShare> findByCost_CostIdAndStatusNot(Integer costId, CostShareStatus status);
    }
