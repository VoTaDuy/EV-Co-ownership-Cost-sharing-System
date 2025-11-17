package com.example.EV.Co_ownership.Cost_sharing.System.Service;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollVoteDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Poll;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.PollVote;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollVoteValue;
import com.example.EV.Co_ownership.Cost_sharing.System.Exception.NotFoundException;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PollRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PollVoteRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PollVoteServiceImp;
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
    public PollVoteDTO vote(Integer pollId, int userId, String voteValueStr) {
        Poll poll = pollRepo.findById(pollId)
                .orElseThrow(() -> new NotFoundException("Poll not found: " + pollId));

        PollVoteValue voteValue;
        try {
            voteValue = PollVoteValue.valueOf(voteValueStr.toUpperCase()); // chuyển chữ thường sang chữ hoa
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Vote value must be YES, NO, or ABSTAIN");
        }

        PollVote vote = voteRepo.findByPoll_PollIdAndUserId(pollId, userId)
                .orElse(PollVote.builder()
                        .poll(poll)
                        .userId(userId)
                        .build());

        vote.setVoteValue(voteValue);
        voteRepo.save(vote);
        return PollVoteDTO.fromEntity(vote);
    }

    @Override
    public void deleteByPoll(Integer pollId) {
        voteRepo.deleteByPoll_PollId(pollId);
    }
}