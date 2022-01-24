package com.stackroute.soulmateservice.service;

import com.stackroute.soulmateservice.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate){
        super();
        this.rabbitTemplate=rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void sendMessageToRabbitMq(User user)
    {
        System.out.println("In producer method");
        rabbitTemplate.convertAndSend(exchange,routingkey, user);
    }
}
