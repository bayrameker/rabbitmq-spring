package com.backend.server.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("crudQueue", false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("testExchange");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    public Queue firstQueue() {
        return new Queue("firstQueue", true);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue("secondQueue", true);
    }

    @Bean
    public Binding bindingFirstQueue(Queue firstQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(firstQueue).to(topicExchange).with("first.*");
    }

    @Bean
    public Binding bindingSecondQueue(Queue secondQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(secondQueue).to(topicExchange).with("second.*");
    }

    // Binding tanımlaması gerekiyorsa burada yapılabilir
}