package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollVoteValue;

import java.time.LocalDateTime;

public class PollVotesDTO {
    private int votes_id;
    private int poll_id;
    private int user_id;
    private PollVoteValue status;
    private LocalDateTime vote_at;

    public int getVotes_id() {
        return votes_id;
    }

    public void setVotes_id(int votes_id) {
        this.votes_id = votes_id;
    }

    public int getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(int poll_id) {
        this.poll_id = poll_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public PollVoteValue getStatus() {
        return status;
    }

    public void setStatus(PollVoteValue status) {
        this.status = status;
    }

    public LocalDateTime getVote_at() {
        return vote_at;
    }

    public void setVote_at(LocalDateTime vote_at) {
        this.vote_at = vote_at;
    }
}
