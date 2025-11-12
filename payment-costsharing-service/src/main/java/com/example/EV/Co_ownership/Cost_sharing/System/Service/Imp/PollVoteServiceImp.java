package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollVoteDTO;

import java.util.List;

public interface PollVoteServiceImp {
    List<PollVoteDTO> getByPoll(Integer pollId);
    PollVoteDTO vote(Integer pollId, String userId, String voteValue);
    void deleteByPoll(Integer pollId);
}