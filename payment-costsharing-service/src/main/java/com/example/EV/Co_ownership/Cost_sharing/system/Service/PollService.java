package com.example.EV.Co_ownership.Cost_sharing.system.Service;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.PollDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.Entity.Poll;
import com.example.EV.Co_ownership.Cost_sharing.system.Entity.VehicleCost;
import com.example.EV.Co_ownership.Cost_sharing.system.Enum.PollStatus;
import com.example.EV.Co_ownership.Cost_sharing.system.Exception.NotFoundException;
import com.example.EV.Co_ownership.Cost_sharing.system.Repository.PollRepository;
import com.example.EV.Co_ownership.Cost_sharing.system.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.PollServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PollService implements PollServiceImp {

    private final PollRepository pollRepo;
    private final VehicleCostRepository costRepo;

    @Override
    public List<PollDTO> getAll(Integer groupId) {
        if (groupId != null) {
            return pollRepo.findByGroupId(groupId).stream().map(PollDTO::fromEntity).toList();
        }
        return pollRepo.findAll().stream().map(PollDTO::fromEntity).toList();
    }


    @Override
    public PollDTO getById(Integer pollId) {
        Poll poll = pollRepo.findById(pollId)
                .orElseThrow(() -> new NotFoundException("Poll not found: " + pollId));
        return PollDTO.fromEntity(poll);
    }

    @Override
    public PollDTO createPoll(PollDTO dto, int userId) {
        Poll poll = Poll.builder()
                .groupId(dto.getGroupId())
                .description(dto.getDescription())
                .createdBy(userId)
                .expiresAt(dto.getExpiresAt())
                .status(PollStatus.active)
                .build();

        if (dto.getCostId() != null) {
            VehicleCost cost = costRepo.findById(dto.getCostId())
                    .orElseThrow(() -> new NotFoundException("Cost not found: " + dto.getCostId()));
            poll.setCost(cost);
        }

        pollRepo.save(poll);
        return PollDTO.fromEntity(poll);
    }

    @Override
    public PollDTO closePoll(Integer pollId, int userId) {
        Poll poll = pollRepo.findById(pollId)
                .orElseThrow(() -> new NotFoundException("Poll not found: " + pollId));
        poll.setStatus(PollStatus.closed);
        pollRepo.save(poll);
        return PollDTO.fromEntity(poll);
    }

    @Override
    public void deletePoll(Integer pollId) {
        pollRepo.deleteById(pollId);
    }
}