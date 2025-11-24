package com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.PollVoteDTO;

import java.util.List;

public interface PollVoteServiceImp {
    List<PollVoteDTO> getByPoll(Integer pollId);
    PollVoteDTO vote(Integer pollId, int userId, String voteValue, int groupId);
    void deleteByPoll(Integer pollId);

}