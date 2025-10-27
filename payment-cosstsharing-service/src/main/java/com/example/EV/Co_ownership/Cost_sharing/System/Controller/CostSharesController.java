package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.CostSharesDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Payloads.ResponseData;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.CostSharesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cost-shares")
public class CostSharesController {

    @Autowired
    CostSharesServiceImp costSharesServiceImp;

    @GetMapping("/getShare")
    public ResponseEntity<?> getCostShare(@RequestParam int share_id) {
        ResponseData responseData = new ResponseData();
        CostSharesDTO costShare = costSharesServiceImp.getCostShareById(share_id);
        if (costShare == null) {
            responseData.setDesc("Không tìm thấy dữ liệu chia phí");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setData(costShare);
        responseData.setDesc("Lấy chi tiết chia phí thành công");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCostShares() {
        ResponseData responseData = new ResponseData();
        List<CostSharesDTO> list = costSharesServiceImp.getAllCostShares();
        responseData.setData(list);
        responseData.setDesc("Lấy danh sách chia phí thành công");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCostShare(@RequestBody CostSharesDTO dto) {
        ResponseData responseData = new ResponseData();
        CostSharesDTO created = costSharesServiceImp.createCostShare(dto);
        if (created == null) {
            responseData.setDesc("Không thể tạo chia phí do chi phí không tồn tại");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
        responseData.setData(created);
        responseData.setDesc("Tạo mới chia phí thành công");
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCostShare(@RequestParam int share_id, @RequestBody CostSharesDTO dto) {
        ResponseData responseData = new ResponseData();
        CostSharesDTO updated = costSharesServiceImp.updateCostShare(share_id, dto);
        if (updated == null) {
            responseData.setDesc("Không tìm thấy dữ liệu để cập nhật");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setData(updated);
        responseData.setDesc("Cập nhật chia phí thành công");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCostShare(@RequestParam int share_id) {
        ResponseData responseData = new ResponseData();
        boolean deleted = costSharesServiceImp.deleteCostShare(share_id);
        if (!deleted) {
            responseData.setDesc("Không tìm thấy dữ liệu để xóa");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setDesc("Xóa chia phí thành công");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
