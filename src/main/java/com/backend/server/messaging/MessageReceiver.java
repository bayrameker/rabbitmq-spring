package com.backend.server.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @RabbitListener(queues = "testQueue")
    public void receive(String message) {
        System.out.println("Received Message: " + message);
    }
}