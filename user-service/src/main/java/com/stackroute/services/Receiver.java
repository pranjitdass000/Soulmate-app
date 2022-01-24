package com.stackroute.services;

import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Receiver implements RabbitListenerConfigurer {
    private UserService userService;
    @Autowired
    public Receiver(UserService userService) {

        this.userService = userService;
    }

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveMessageToRabbitMq(User user) throws UserAlreadyExistsException {
        System.out.println("i am consumer" + user);

        userService.saveUser(user);
    }
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
