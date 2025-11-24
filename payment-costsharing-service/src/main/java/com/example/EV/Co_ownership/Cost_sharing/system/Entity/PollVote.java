package com.example.EV.Co_ownership.Cost_sharing.system.Entity;

import com.example.EV.Co_ownership.Cost_sharing.system.Enum.PollVoteValue;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "poll_votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"poll_id", "user_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PollVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Integer voteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @Column(name = "user_id")
    private int userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote_value")
    private PollVoteValue voteValue;

    @CreationTimestamp
    @Column(name = "voted_at", updatable = false)
    private LocalDateTime votedAt;

}
