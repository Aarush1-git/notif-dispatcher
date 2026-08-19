package com.notif.dispatcher;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationWorker {

    private final RateLimiter rateLimiter;

    public NotificationWorker(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @RabbitListener(queues = "test-queue")
    public void receiveMessage(NotificationMessage msg) {
        rateLimiter.acquire(); // waits here if we're going too fast
        System.out.println(System.currentTimeMillis() + " Received " + msg);
    }
}