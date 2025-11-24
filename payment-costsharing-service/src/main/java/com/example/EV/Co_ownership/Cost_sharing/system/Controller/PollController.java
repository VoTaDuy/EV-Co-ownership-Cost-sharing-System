package com.example.EV.Co_ownership.Cost_sharing.system.Controller;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.PollDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.PollServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment/polls")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PollController {

    private final PollServiceImp pollService;

    @GetMapping
    public List<PollDTO> getAll(@RequestParam(required = false) Integer groupId) {
        return pollService.getAll(groupId);
    }

    @GetMapping("/{id}")
    public PollDTO getById(@PathVariable Integer id) {
        return pollService.getById(id);
    }

    @PostMapping
    public PollDTO createPoll(@RequestBody PollDTO dto,
                              @RequestHeader("userId") int userId) {
        return pollService.createPoll(dto, userId);
    }

    @PostMapping("/{id}/close")
    public PollDTO closePoll(@PathVariable Integer id,
                             @RequestHeader("userId") int userId) {
        return pollService.closePoll(id, userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Integer id) {
        pollService.deletePoll(id);
        return ResponseEntity.noContent().build();
    }
}