package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PollServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PollController {

    private final PollServiceImp pollService;

    @GetMapping
    public List<PollDTO> getAll(@RequestParam(required = false) String groupId) {
        return pollService.getAll(groupId);
    }

    @GetMapping("/{id}")
    public PollDTO getById(@PathVariable Integer id) {
        return pollService.getById(id);
    }

    @PostMapping
    public PollDTO createPoll(@RequestBody PollDTO dto,
                              @RequestHeader("userId") String userId) {
        return pollService.createPoll(dto, userId);
    }

    @PostMapping("/{id}/close")
    public PollDTO closePoll(@PathVariable Integer id,
                             @RequestHeader("userId") String userId) {
        return pollService.closePoll(id, userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Integer id) {
        pollService.deletePoll(id);
        return ResponseEntity.noContent().build();
    }
}