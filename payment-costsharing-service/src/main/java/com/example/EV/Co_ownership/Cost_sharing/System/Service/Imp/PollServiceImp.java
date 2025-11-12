package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollDTO;

import java.util.List;

public interface PollServiceImp {
    List<PollDTO> getAll(String groupId);
    PollDTO getById(Integer pollId);
    PollDTO createPoll(PollDTO dto, String userId);
    PollDTO closePoll(Integer pollId, String userId);
    void deletePoll(Integer pollId);
}
