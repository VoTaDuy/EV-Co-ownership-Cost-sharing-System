package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;


import com.example.EV.Co_ownership.Cost_sharing.System.Entity.PollVotes;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollVoteValue;

import java.util.List;
import java.util.Map;

public interface PollVotesServiceImp {
    PollVotes createVote(int pollId, int userId, PollVoteValue value);
    List<PollVotes> getVotesByPoll(int pollId);
    List<PollVotes> getVotesByUser(int userId);
    void deleteVote(int voteId);
    Map<PollVoteValue, Long> getPollResult(int pollId);
}