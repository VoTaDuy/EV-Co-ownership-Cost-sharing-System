package com.example.EV_Co_ownership.User_and_ownership_service.Messaging;

import com.example.EV_Co_ownership.User_and_ownership_service.Utils.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OwnershipProducer {
    private final RabbitTemplate rabbitTemplate;

    public OwnershipProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCreateOwnershipGroup(Object data) {
        System.out.println("📤 Sending message to queue: " + data);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE, data);
    }
}
