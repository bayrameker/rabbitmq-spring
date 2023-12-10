package com.backend.server.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListeners {

    @RabbitListener(queues = "firstQueue")
    public void handleFirstQueue(String message) {
        System.out.println("Received on firstQueue: " + message);
    }

    @RabbitListener(queues = "secondQueue")
    public void handleSecondQueue(String message) {
        System.out.println("Received on secondQueue: " + message);
    }
}
