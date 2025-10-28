package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollVoteValue;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Entity(name = "poll_votes")
public class PollVotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vote_id;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Polls poll_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "vote_value")
    private PollVoteValue vote_value;

    @Column(name = "vote_at")
    private LocalDateTime vote_at;

    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public Polls getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(Polls poll_id) {
        this.poll_id = poll_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public PollVoteValue getVote_value() {
        return vote_value;
    }

    public void setVote_value(PollVoteValue vote_value) {
        this.vote_value = vote_value;
    }

    public LocalDateTime getVote_at() {
        return vote_at;
    }

    public void setVote_at(LocalDateTime vote_at) {
        this.vote_at = vote_at;
    }
}
