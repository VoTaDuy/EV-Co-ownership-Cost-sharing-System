package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.PollVotes;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollVoteValue;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.PollVotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/poll-votes")
public class PollVotesController {

    @Autowired
    private PollVotesService pollVotesService;

    @PostMapping("/create")
    public ResponseEntity<?> createVote(@RequestBody Map<String, Object> request) {
        try {
            int pollId = (int) request.get("pollId");
            int userId = (int) request.get("userId");
            String voteValueStr = ((String) request.get("voteValue")).toUpperCase();
            PollVoteValue voteValue = PollVoteValue.valueOf(voteValueStr);

            PollVotes vote = pollVotesService.createVote(pollId, userId, voteValue);
            return ResponseEntity.ok(vote);

        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", ex.getMessage(),
                    "timestamp", LocalDateTime.now(),
                    "status", 400
            ));
        }
    }

    @GetMapping("/poll/{pollId}")
    public ResponseEntity<?> getVotesByPoll(@PathVariable int pollId) {
        try {
            return ResponseEntity.ok(pollVotesService.getVotesByPoll(pollId));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVotesByUser(@PathVariable int userId) {
        return ResponseEntity.ok(pollVotesService.getVotesByUser(userId));
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<?> deleteVote(@PathVariable int voteId) {
        try {
            pollVotesService.deleteVote(voteId);
            return ResponseEntity.ok(Map.of("message", "Xóa phiếu vote thành công!"));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping("/result/{pollId}")
    public ResponseEntity<?> getPollResult(@PathVariable int pollId) {
        try {
            return ResponseEntity.ok(pollVotesService.getPollResult(pollId));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        }
    }

}