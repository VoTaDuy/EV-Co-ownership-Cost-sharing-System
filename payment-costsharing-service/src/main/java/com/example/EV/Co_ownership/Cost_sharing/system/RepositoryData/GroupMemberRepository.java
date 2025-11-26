package com.example.EV.Co_ownership.Cost_sharing.system.RepositoryData;

import com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {
    List<GroupMember> findByUserId(int userId);
}
