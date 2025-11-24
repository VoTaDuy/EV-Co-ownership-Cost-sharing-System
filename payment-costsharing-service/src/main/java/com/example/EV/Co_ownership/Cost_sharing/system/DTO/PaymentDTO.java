package com.example.EV.Co_ownership.Cost_sharing.system.DTO;

import com.example.EV.Co_ownership.Cost_sharing.system.Entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Integer paymentId;
    private int groupId;
    private int userId;
    private Integer costId;
    private Integer fundId;
    private BigDecimal amount;
    private String gateway;
    private String gatewayOrderId;
    private String gatewayResponse;
    private LocalDateTime paymentDate;
    private String status;
    private LocalDateTime createdAt;

    public static PaymentDTO fromEntity(Payment payment) {
        return PaymentDTO.builder()
                .paymentId(payment.getPaymentId())
                .groupId(payment.getGroupId())
                .userId(payment.getUserId())
                .costId(payment.getCost() != null ? payment.getCost().getCostId() : null)
                .fundId(payment.getFund() != null ? payment.getFund().getFundId() : null)
                .amount(payment.getAmount())
                .gateway(payment.getGateway())
                .gatewayOrderId(payment.getGatewayOrderId())
                .gatewayResponse(payment.getGatewayResponse())
                .paymentDate(payment.getPaymentDate())
                .status(payment.getStatus().name())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}