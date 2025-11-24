package com.example.EV.Co_ownership.Cost_sharing.system.RepositoryData;


import com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse.OwnershipGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnershipGroupRepository extends JpaRepository<OwnershipGroup, Integer> {
    OwnershipGroup findByGroupId(int groupId);
}
