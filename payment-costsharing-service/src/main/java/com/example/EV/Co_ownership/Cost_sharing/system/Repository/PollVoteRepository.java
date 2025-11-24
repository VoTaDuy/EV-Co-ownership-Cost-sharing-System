package com.example.EV.Co_ownership.Cost_sharing.system.Repository;

import com.example.EV.Co_ownership.Cost_sharing.system.Entity.PollVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PollVoteRepository extends JpaRepository<PollVote, Integer> {
    List<PollVote> findByPoll_PollId(Integer pollId);
    Optional<PollVote> findByPoll_PollIdAndUserId(Integer pollId, int userId);
    void deleteByPoll_PollId(Integer pollId);
}