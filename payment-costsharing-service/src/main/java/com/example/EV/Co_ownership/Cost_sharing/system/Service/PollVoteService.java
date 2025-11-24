package com.example.EV.Co_ownership.Cost_sharing.system.Service;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.PollVoteDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.Entity.Poll;
import com.example.EV.Co_ownership.Cost_sharing.system.Entity.PollVote;
import com.example.EV.Co_ownership.Cost_sharing.system.Enum.PollVoteValue;
import com.example.EV.Co_ownership.Cost_sharing.system.Exception.NotFoundException;
import com.example.EV.Co_ownership.Cost_sharing.system.Repository.PollRepository;
import com.example.EV.Co_ownership.Cost_sharing.system.Repository.PollVoteRepository;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.PollVoteServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PollVoteService implements PollVoteServiceImp {

    private final PollVoteRepository voteRepo;
    private final PollRepository pollRepo;

    @Override
    public List<PollVoteDTO> getByPoll(Integer pollId) {
        return voteRepo.findByPoll_PollId(pollId).stream()
                .map(PollVoteDTO::fromEntity)
                .toList();
    }

    @Override
    public PollVoteDTO vote(Integer pollId, int userId, String voteValueStr, int groupId) {
        Poll poll = pollRepo.findById(pollId)
                .orElseThrow(() -> new NotFoundException("Poll not found: " + pollId));

        PollVoteValue voteValue;
        try {
            voteValue = PollVoteValue.valueOf(voteValueStr);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException( "Vote value must be yes, no, or abstain");
        }

        PollVote vote = voteRepo.findByPoll_PollIdAndUserId(pollId, userId)
                .orElseGet(() -> PollVote.builder()
                        .poll(poll)
                        .userId(userId)
                        .build());

        vote.setVoteValue(voteValue);
        vote = voteRepo.save(vote);

        return PollVoteDTO.fromEntity(vote);
    }

    @Override
    public void deleteByPoll(Integer pollId) {
        voteRepo.deleteByPoll_PollId(pollId);
    }
}