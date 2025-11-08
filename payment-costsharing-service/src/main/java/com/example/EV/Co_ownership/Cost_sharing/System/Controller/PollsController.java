package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollsDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Payloads.ResponseData;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PollsServiceImp;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.PollsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/polls")
public class PollsController {

    @Autowired
    PollsServiceImp pollsServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPolls() {
        ResponseData responseData = new ResponseData();
        List<PollsDTO> list = pollsServiceImp.getAllPolls();

        responseData.setDesc("Lấy danh sách poll thành công");
        responseData.setData(list);
        responseData.setSuccess(true);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @GetMapping("/getPolls")
    public ResponseEntity<ResponseData> getPoll(@RequestParam int poll_id) {
        ResponseData responseData = new ResponseData();
        PollsDTO poll = pollsServiceImp.getPollById(poll_id);

        if (poll == null) {
            responseData.setStatus(HttpStatus.NOT_FOUND.value());
            responseData.setDesc("Không tìm thấy poll với ID: " + poll_id);
            responseData.setData(null);
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }

        responseData.setStatus(HttpStatus.OK.value());
        responseData.setDesc("Lấy thông tin poll thành công");
        responseData.setData(poll);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/createPolls")
    public ResponseEntity<?> createPolls(@RequestBody PollsDTO dto) {
        ResponseData responseData = new ResponseData();
        PollsDTO created = pollsServiceImp.createPolls(dto);

        responseData.setData(created);
        responseData.setDesc("Tao Polls");
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePoll(
            @RequestParam int poll_id,
            @RequestParam String created_by,
            @RequestBody PollsDTO dto) {

        ResponseData responseData = new ResponseData();
        PollsDTO updated = pollsServiceImp.updatePolls(poll_id, dto, created_by);

        if (updated == null) {
            responseData.setDesc("Không tìm thấy poll để cập nhật");
            responseData.setSuccess(false);
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }

        responseData.setDesc("Cập nhật poll thành công");
        responseData.setData(updated);
        responseData.setSuccess(true);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePoll(@RequestParam int poll_id) {
        try {
            pollsServiceImp.deletePollById(poll_id);
            return ResponseEntity.ok(Map.of(
                    "desc", "Xóa poll thành công",
                    "success", true
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "desc", e.getMessage(),
                    "success", false
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "desc", "Lỗi khi xóa poll: " + e.getMessage(),
                    "success", false
            ));
        }
    }

    @PutMapping("/close")
    public ResponseEntity<?> closePoll(
            @RequestParam int poll_id,
            @RequestParam String created_by) {
        ResponseData responseData = new ResponseData();

        try {
            PollsDTO closed = pollsServiceImp.closePoll(poll_id, created_by);

            if (closed == null) {
                responseData.setDesc("Không tìm thấy poll để đóng");
                responseData.setSuccess(false);
                return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
            }

            responseData.setDesc("Đóng poll thành công");
            responseData.setData(closed);
            responseData.setSuccess(true);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (RuntimeException e) {
            responseData.setDesc(e.getMessage());
            responseData.setSuccess(false);
            return new ResponseEntity<>(responseData, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            responseData.setDesc("Lỗi khi đóng poll: " + e.getMessage());
            responseData.setSuccess(false);
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}