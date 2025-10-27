package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollsDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Polls;

import java.util.List;

public interface PollsServiceImp {
    Polls findById(int poll_id);
    List<Polls> getAllPolls();
    PollsDTO createPolls(PollsDTO dto);
    boolean deleteById(int poll_id);
}
