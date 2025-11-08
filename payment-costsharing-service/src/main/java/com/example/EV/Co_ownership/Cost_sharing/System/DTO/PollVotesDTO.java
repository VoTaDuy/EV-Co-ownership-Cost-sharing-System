package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollVoteValue;

import java.time.LocalDateTime;

public class PollVotesDTO {
    private int vote_id;
    private int id;
    private String user_id;
    private PollVoteValue vote_value;
    private LocalDateTime vote_at;

    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
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