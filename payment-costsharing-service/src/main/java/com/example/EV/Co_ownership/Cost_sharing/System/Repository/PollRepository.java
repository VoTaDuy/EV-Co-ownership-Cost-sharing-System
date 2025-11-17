package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Poll;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {
    List<Poll> findByGroupId(int groupId);
    List<Poll> findByStatus(PollStatus status);
}