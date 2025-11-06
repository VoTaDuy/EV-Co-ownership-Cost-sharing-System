package com.example.EV.Co_ownership.Cost_sharing.System.Service;


import com.example.EV.Co_ownership.Cost_sharing.System.Entity.PollVotes;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Polls;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollStatus;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollVoteValue;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PollVotesRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.PollsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class PollVotesService {

    @Autowired
    private PollVotesRepository pollVotesRepository;

    @Autowired
    private PollsRepository pollsRepository;

    public PollVotes createVote(int pollId, int userId, PollVoteValue value) {
        Polls poll = pollsRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll không tồn tại!"));

        if (poll.getStatus() == PollStatus.CLOSED) {
            throw new RuntimeException("Poll đã đóng, không thể vote!");
        }

        if (pollVotesRepository.findByPollAndUser_id(poll, userId).isPresent()) {
            throw new RuntimeException("Người dùng này đã vote!");
        }

        PollVotes vote = new PollVotes();
        vote.setPoll(poll);
        vote.setUser_id(userId);
        vote.setVote_value(value);
        vote.setVote_at(LocalDateTime.now());

        return pollVotesRepository.save(vote);
    }

    public List<PollVotes> getVotesByPoll(int pollId) {
        Polls poll = pollsRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll không tồn tại!"));
        return pollVotesRepository.findByPoll(poll);
    }

    public List<PollVotes> getVotesByUser(int userId) {
        return pollVotesRepository.findByUser_id(userId);
    }

    public void deleteVote(int voteId) {
        if (!pollVotesRepository.existsById(voteId)) {
            throw new RuntimeException("Phiếu vote không tồn tại!");
        }
        pollVotesRepository.deleteById(voteId);
    }

    public Map<PollVoteValue, Long> getPollResult(int pollId) {
        Polls poll = pollsRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll không tồn tại!"));
        List<Object[]> results = pollVotesRepository.countVotesByValue(poll);

        Map<PollVoteValue, Long> stats = new EnumMap<>(PollVoteValue.class);
        for (Object[] r : results) {
            stats.put((PollVoteValue) r[0], (Long) r[1]);
        }
        return stats;
    }

}