package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Poll;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollStatus;
import com.example.EV.Co_ownership.Cost_sharing.System.Exception.NotFoundException;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PollRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PollServiceImp;
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
    public List<PollDTO> getAll(String groupId) {
        if (groupId != null && !groupId.isBlank()) {
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
    public PollDTO createPoll(PollDTO dto, String userId) {
        Poll poll = Poll.builder()
                .groupId(dto.getGroupId())
                .description(dto.getDescription())
                .createdBy(userId)
                .expiresAt(dto.getExpiresAt())
                .status(PollStatus.ACTIVE)
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
    public PollDTO closePoll(Integer pollId, String userId) {
        Poll poll = pollRepo.findById(pollId)
                .orElseThrow(() -> new NotFoundException("Poll not found: " + pollId));
        poll.setStatus(PollStatus.CLOSED);
        pollRepo.save(poll);
        return PollDTO.fromEntity(poll);
    }

    @Override
    public void deletePoll(Integer pollId) {
        pollRepo.deleteById(pollId);
    }
}