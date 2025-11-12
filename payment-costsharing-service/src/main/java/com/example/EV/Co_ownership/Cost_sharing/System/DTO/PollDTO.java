package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Poll;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PollDTO {
    private Integer pollId;
    private String groupId;
    private Integer costId;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private String status;

    public static PollDTO fromEntity(Poll poll) {
        return PollDTO.builder()
                .pollId(poll.getPollId())
                .groupId(poll.getGroupId())
                .costId(poll.getCost() != null ? poll.getCost().getCostId() : null)
                .description(poll.getDescription())
                .createdBy(poll.getCreatedBy())
                .createdAt(poll.getCreatedAt())
                .expiresAt(poll.getExpiresAt())
                .status(poll.getStatus().name())
                .build();
    }
}