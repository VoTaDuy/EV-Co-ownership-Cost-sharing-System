package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PaymentDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupFund;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Payments;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.GroupFundRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Repository.VehicleCostRepository;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PaymentsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentsController {

    @Autowired
    private PaymentsServiceImp paymentsServiceImp;

    @Autowired
    private VehicleCostRepository vehicleCostRepository;


    @Autowired
    private GroupFundRepository groupFundRepository;

    // Tạo payment mới
    @PostMapping("/create")
    public ResponseEntity<Payments> createPayment(@RequestBody PaymentDTO dto) {

        // Lấy các entity từ DB, tránh transient object
        VehicleCost cost = vehicleCostRepository.findById(dto.getCost_id())
                .orElseThrow(() -> new RuntimeException("VehicleCost not found"));


        GroupFund fund = null;
        if (dto.getFund_id() > 0) {
            fund = groupFundRepository.findById(dto.getFund_id())
                    .orElseThrow(() -> new RuntimeException("GroupFund not found"));
        }

        Payments payment = new Payments();
        payment.setCost_id(cost);
        payment.setFund_id(fund);
        payment.setUser_id(dto.getUser_id());

        payment.setAmount(dto.getAmount());
        payment.setPayment_date(dto.getPayment_date());
        payment.setStatus(dto.getStatus());

        Payments savedPayment = paymentsServiceImp.savePayment(payment);
        return ResponseEntity.ok(savedPayment);
    }

    // Lấy tất cả payment
    @GetMapping("/all")
    public ResponseEntity<List<Payments>> getAllPayments() {
        return ResponseEntity.ok(paymentsServiceImp.getAllPayments());
    }

    // Lấy payment theo id
    @GetMapping("/{id}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable int id) {
        return paymentsServiceImp.getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Xóa payment theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable int id) {
        paymentsServiceImp.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy payment theo group
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Payments>> getPaymentsByGroup(@PathVariable String groupId) {
        return ResponseEntity.ok(paymentsServiceImp.getPaymentsByGroupId(groupId));
    }

    // Lấy payment theo user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payments>> getPaymentsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(paymentsServiceImp.getPaymentsByUserId(userId));
    }
}
