package com.notif.dispatcher;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class NotificationWorker {
    @RabbitListener(queues = "test-queue")
    public void worker(NotificationMessage msg){
        System.out.println("Received " + msg);
    }
}
