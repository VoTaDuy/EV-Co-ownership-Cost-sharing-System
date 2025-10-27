package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollsDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Polls;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PollsRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PollsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PollsService implements PollsServiceImp {
    @Autowired
    private PollsRepository pollsRepository;
    @Autowired
    private VehicleCostRepository vehicleCostRepository;


    @Override
    public Polls findById(int poll_id) {
        return pollsRepository.findById(poll_id);
    }

    @Override
    public List<Polls> getAllPolls() {
        return List.of();
    }


    @Override
    public PollsDTO createPolls(PollsDTO dto) {
        Polls polls = new Polls();
        polls.setGroup_id(dto.getGroup_id());
        polls.setCost_id(dto.getCost_id());
        polls.setDescription(dto.getDescription());
        polls.setCreated_by(dto.getCreated_by());
        polls.setCreated_at(dto.getCreated_at());
        polls.setExpires_at(dto.getExpires_at());
        polls.setStatus(dto.getStatus());


        Polls saved = pollsRepository.save(polls);

        PollsDTO savedDTO = new PollsDTO();
        savedDTO.setGroup_id(polls.getGroup_id());
        savedDTO.setCost_id(polls.getCost_id());
        savedDTO.setDescription(polls.getDescription());
        savedDTO.setCreated_by(polls.getCreated_by());
        savedDTO.setCreated_at(polls.getCreated_at());
        savedDTO.setExpires_at(polls.getExpires_at());
        savedDTO.setStatus(polls.getStatus());
        return savedDTO;
    }

    @Override
    public boolean deleteById(int poll_id) {
        if(pollsRepository.existsById(poll_id)){
            return false;
        }
        pollsRepository.deleteById(poll_id);
        return true;
    }


}
