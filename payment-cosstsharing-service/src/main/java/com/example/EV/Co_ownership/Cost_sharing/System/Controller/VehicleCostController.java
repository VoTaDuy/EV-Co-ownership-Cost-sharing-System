package com.example.EV.Co_ownership.Cost_sharing.System.Controller;


import com.example.EV.Co_ownership.Cost_sharing.System.DTO.VehicleCostDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;
import com.example.EV.Co_ownership.Cost_sharing.System.Payloads.ResponseData;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.VehicleCostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/cost")
public class VehicleCostController {

    @Autowired
    VehicleCostServiceImp vehicleCostServiceImp;


    @GetMapping("/getCost")
    public ResponseEntity<?> getCost(@RequestParam int cost_id)
    {
        ResponseData responseData = new ResponseData();
        vehicleCostServiceImp.getVehicleCostById(cost_id);
        responseData.setData(vehicleCostServiceImp.getVehicleCostById(cost_id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAllCosts(@RequestParam int cost_id) {
        ResponseData responseData = new ResponseData();
        List<VehicleCostDTO> list = vehicleCostServiceImp.getAllVehicleCost();

        responseData.setData(list);
        responseData.setDesc("Lấy danh sách chi phí thành công");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCost(@RequestBody VehicleCostDTO dto) {
        ResponseData responseData = new ResponseData();
        VehicleCostDTO created = vehicleCostServiceImp.createVehicleCost(dto);

        responseData.setData(created);
        responseData.setDesc("Tạo mới chi phí thành công");
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCost(@RequestParam int cost_id, @RequestBody VehicleCostDTO dto) {
        ResponseData responseData = new ResponseData();
        VehicleCostDTO updated = vehicleCostServiceImp.updateVehicleCost(cost_id, dto);

        if (updated == null) {
            responseData.setDesc("Không tìm thấy dữ liệu để cập nhật");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }

        responseData.setData(updated);
        responseData.setDesc("Cập nhật chi phí thành công");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCost(@RequestParam int cost_id) {
        ResponseData responseData = new ResponseData();
        boolean deleted = vehicleCostServiceImp.deleteVehicleCost(cost_id);

        if (!deleted) {
            responseData.setDesc("Không tìm thấy dữ liệu để xóa");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }

        responseData.setDesc("Xóa chi phí thành công");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
