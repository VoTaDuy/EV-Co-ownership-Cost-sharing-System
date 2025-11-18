package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollVoteDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PollVoteServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment/poll-votes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PollVoteController {

    private final PollVoteServiceImp voteService;

    @GetMapping("/poll/{pollId}")
    public List<PollVoteDTO> getByPoll(@PathVariable Integer pollId) {
        return voteService.getByPoll(pollId);
    }



    @PostMapping("/poll/{pollId}")
    public ResponseEntity<PollVoteDTO> vote(
            @PathVariable Integer pollId,
            @RequestParam String voteValue,
            @RequestHeader("userId") int userId,
            @RequestParam int groupId
    ) {
        PollVoteDTO vote = voteService.vote(pollId, userId, voteValue, groupId);
        return ResponseEntity.ok(vote);
    }

    @DeleteMapping("/poll/{pollId}")
    public ResponseEntity<Void> deleteByPoll(@PathVariable Integer pollId) {
        voteService.deleteByPoll(pollId);
        return ResponseEntity.noContent().build();
    }
}