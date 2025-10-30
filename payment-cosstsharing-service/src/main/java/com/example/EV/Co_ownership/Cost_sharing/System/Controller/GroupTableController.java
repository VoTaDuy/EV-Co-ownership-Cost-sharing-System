package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupTable;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.GroupTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "*")
public class GroupTableController {

    @Autowired
    private GroupTableService groupTableService;

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody GroupTable group) {
        return ResponseEntity.ok(groupTableService.createGroup(group));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GroupTable>> getAllGroups() {
        return ResponseEntity.ok(groupTableService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(groupTableService.getGroupById(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGroup(@PathVariable int id, @RequestBody GroupTable group) {
        try {
            return ResponseEntity.ok(groupTableService.updateGroup(id, group));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable int id) {
        try {
            groupTableService.deleteGroup(id);
            return ResponseEntity.ok("Đã xoá nhóm có ID: " + id);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
