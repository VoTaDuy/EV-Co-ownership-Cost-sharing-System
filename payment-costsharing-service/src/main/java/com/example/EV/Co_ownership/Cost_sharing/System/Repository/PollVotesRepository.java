package com.example.EV.Co_ownership.Cost_sharing.System.Repository;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.PollVotes;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Polls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PollVotesRepository extends JpaRepository<PollVotes, Integer> {

    List<PollVotes> findByPoll(Polls poll);

    @Query("SELECT v FROM poll_votes v WHERE v.user_id = :userId")
    List<PollVotes> findByUser_id(@Param("userId") String userId);

    @Query("SELECT v FROM poll_votes v WHERE v.poll = :poll AND v.user_id = :userId")
    Optional<PollVotes> findByPollAndUser_id(@Param("poll") Polls poll, @Param("userId") String user_id);

    @Query("SELECT v.vote_value, COUNT(v) FROM poll_votes v WHERE v.poll = :poll GROUP BY v.vote_value")
    List<Object[]> countVotesByValue(Polls poll);
}