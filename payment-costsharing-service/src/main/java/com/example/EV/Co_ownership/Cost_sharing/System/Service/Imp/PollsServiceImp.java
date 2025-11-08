package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollsDTO;

import java.util.List;

public interface PollsServiceImp {
    List<PollsDTO> getAllPolls();
    PollsDTO getPollById(int poll_id);
    PollsDTO createPolls(PollsDTO dto);
    PollsDTO updatePolls(int poll_id, PollsDTO dto, String created_by);
    void deletePollById(int poll_id);
    PollsDTO closePoll(int poll_id, String created_by);
}