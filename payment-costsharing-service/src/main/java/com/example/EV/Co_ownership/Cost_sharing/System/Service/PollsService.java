package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollsDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Polls;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollStatus;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PollsRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PollsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PollsService implements PollsServiceImp {
    @Autowired
    private PollsRepository pollsRepository;
    @Autowired
    private VehicleCostRepository vehicleCostRepository;


    @Override
    public List<PollsDTO> getAllPolls() {
        List<Polls> pollsList = pollsRepository.findAll();
        List<PollsDTO> dtoList = new ArrayList<>();

        for (Polls polls : pollsList) {
            PollsDTO dto = new PollsDTO();
            dto.setId(polls.getId());
            dto.setGroup_id(polls.getGroup_id());
            dto.setCost_id(polls.getCost_id());
            dto.setDescription(polls.getDescription());
            dto.setCreated_by(polls.getCreated_by());
            dto.setCreated_at(polls.getCreated_at());
            dto.setExpires_at(polls.getExpires_at());
            dto.setStatus(polls.getStatus());
            dtoList.add(dto);
        }

        return dtoList;
    }


    @Override
    public PollsDTO getPollById(int poll_id) {

        Polls polls = pollsRepository.getPollById((poll_id));

        if (polls == null) {
            return null;
        }

        PollsDTO dto = new PollsDTO();
        dto.setId(polls.getId());
        dto.setGroup_id(polls.getGroup_id());
        dto.setCost_id(polls.getCost_id());
        dto.setDescription(polls.getDescription());
        dto.setCreated_by(polls.getCreated_by());
        dto.setCreated_at(polls.getCreated_at());
        dto.setExpires_at(polls.getExpires_at());
        dto.setStatus(polls.getStatus());

        return dto;
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
    public PollsDTO updatePolls(int poll_id, PollsDTO dto, String created_by) {
        Optional<Polls> optionalPoll = pollsRepository.findById(poll_id);
        if (optionalPoll.isEmpty()) {
            return null;
        }

        Polls existingPoll = optionalPoll.get();

        if (existingPoll.getCreated_by() != created_by) {
            throw new RuntimeException("Bạn không có quyền cập nhật poll này.");
        }

        existingPoll.setGroup_id(dto.getGroup_id());
        existingPoll.setCost_id(dto.getCost_id());
        existingPoll.setDescription(dto.getDescription());
        existingPoll.setExpires_at(dto.getExpires_at());
        existingPoll.setStatus(dto.getStatus());

        Polls updatedPoll = pollsRepository.save(existingPoll);

        PollsDTO updatedDTO = new PollsDTO();
        updatedDTO.setId(updatedPoll.getId());
        updatedDTO.setGroup_id(updatedPoll.getGroup_id());
        updatedDTO.setCost_id(updatedPoll.getCost_id());
        updatedDTO.setDescription(updatedPoll.getDescription());
        updatedDTO.setCreated_by(updatedPoll.getCreated_by());
        updatedDTO.setCreated_at(updatedPoll.getCreated_at());
        updatedDTO.setExpires_at(updatedPoll.getExpires_at());
        updatedDTO.setStatus(updatedPoll.getStatus());

        return updatedDTO;
    }


    @Override
    public void deletePollById(int poll_id) {
        Polls poll = pollsRepository.findById(poll_id).orElse(null);
        if (poll == null) {
            throw new RuntimeException("Không tìm thấy poll với ID: " + poll_id);
        }

        pollsRepository.delete(poll);
    }

    @Override
    public PollsDTO closePoll(int poll_id, String created_by) {
        Polls polls = pollsRepository.findById(poll_id).orElse(null);
        if (polls == null) {
            return null;
        }

        if (polls.getCreated_by() != created_by) {
            throw new RuntimeException("Bạn không có quyền đóng poll này");
        }

        polls.setStatus(PollStatus.CLOSED);
        pollsRepository.save(polls);

        PollsDTO dto = new PollsDTO();
        dto.setId(polls.getId());
        dto.setGroup_id(polls.getGroup_id());
        dto.setCost_id(polls.getCost_id());
        dto.setDescription(polls.getDescription());
        dto.setCreated_by(polls.getCreated_by());
        dto.setCreated_at(polls.getCreated_at());
        dto.setExpires_at(polls.getExpires_at());
        dto.setStatus(polls.getStatus());

        return dto;
    }


}