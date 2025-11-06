package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollVoteValue;

import java.time.LocalDateTime;

public class PollVotesDTO {
    private int vote_id;
    private int poll_id;
    private int user_id;
    private PollVoteValue vote_value;
    private LocalDateTime vote_at;

    public LocalDateTime getVote_at() {
        return vote_at;
    }

    public void setVote_at(LocalDateTime vote_at) {
        this.vote_at = vote_at;
    }

    public PollVoteValue getVote_value() {
        return vote_value;
    }

    public void setVote_value(PollVoteValue vote_value) {
        this.vote_value = vote_value;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(int poll_id) {
        this.poll_id = poll_id;
    }

    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }
}