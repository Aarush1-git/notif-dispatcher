package com.notif.dispatcher;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RabbitTestController {

    @Bean
    public Queue testQueue() {
        return new Queue("test-queue", true);
    }

    private final RabbitTemplate rabbitTemplate;

    public RabbitTestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/rabbit-test")
    public String rabbitTest() {
        NotificationMessage msg = new NotificationMessage(UUID.randomUUID().toString(), "test@example.com", 0);
        rabbitTemplate.convertAndSend("test-queue", msg);
        return "sent: " + msg;
    }
}