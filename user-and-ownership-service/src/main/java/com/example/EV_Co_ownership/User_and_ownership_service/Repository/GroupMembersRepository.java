package com.example.EV_Co_ownership.User_and_ownership_service.Repository;

import com.example.EV_Co_ownership.User_and_ownership_service.Embeddable.GroupMemberId;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.GroupMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GroupMembersRepository extends JpaRepository<GroupMembers, GroupMemberId> {

    @Query("SELECT SUM(gm.ownershipRatio) FROM GroupMembers gm WHERE gm.group.groupId = :groupId")
    BigDecimal findSumRatioByGroupId(@Param("groupId") int groupId);

    @Query("SELECT gm FROM GroupMembers gm WHERE gm.group.groupId = :groupId")
    List<GroupMembers> findAllByGroupId(@Param("groupId") int groupId);
}