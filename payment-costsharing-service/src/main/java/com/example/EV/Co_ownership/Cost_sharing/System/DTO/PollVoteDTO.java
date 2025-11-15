package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.PollVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PollVoteDTO {
    private Integer voteId;
    private Integer pollId;
    private int userId;
    private String voteValue;
    private LocalDateTime votedAt;

    public static PollVoteDTO fromEntity(PollVote vote) {
        return PollVoteDTO.builder()
                .voteId(vote.getVoteId())
                .pollId(vote.getPoll() != null ? vote.getPoll().getPollId() : null)
                .userId(vote.getUserId())
                .voteValue(vote.getVoteValue().name())
                .votedAt(vote.getVotedAt())
                .build();
    }
}