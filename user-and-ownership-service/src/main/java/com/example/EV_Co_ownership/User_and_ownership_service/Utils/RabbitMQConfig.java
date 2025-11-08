package com.example.EV_Co_ownership.User_and_ownership_service.Utils;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE = "ownership_queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false);
    }
}
