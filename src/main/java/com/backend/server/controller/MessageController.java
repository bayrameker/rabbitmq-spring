package com.backend.server.controller;

import com.backend.server.messaging.MessageSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        messageSender.send(message);
        return "Tamam bu: " + message;
    }

    @PostMapping("/{routingKey}")
    public String sendMessage(@PathVariable String routingKey, @RequestBody String message) {
        rabbitTemplate.convertAndSend("topic-exchange", routingKey, message);
        return "Message sent to " + routingKey + "!";
    }
}