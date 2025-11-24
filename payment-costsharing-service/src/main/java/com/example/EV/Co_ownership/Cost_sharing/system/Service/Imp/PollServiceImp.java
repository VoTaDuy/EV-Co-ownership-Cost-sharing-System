package com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.PollDTO;

import java.util.List;

public interface PollServiceImp {
    List<PollDTO> getAll(Integer groupId);
    PollDTO getById(Integer pollId);
    PollDTO createPoll(PollDTO dto, int userId);
    PollDTO closePoll(Integer pollId, int userId);
    void deletePoll(Integer pollId);
}
