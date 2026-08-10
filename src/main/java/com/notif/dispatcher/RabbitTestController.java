package com.notif.dispatcher;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitTestController {

    // Declares a queue called "test-queue" on startup if it doesn't exist yet.
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
        rabbitTemplate.convertAndSend("test-queue", "hello from spring boot");
        return "message sent to test-queue";
    }
}