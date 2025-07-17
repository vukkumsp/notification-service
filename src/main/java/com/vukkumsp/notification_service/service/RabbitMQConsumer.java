package com.vukkumsp.notification_service.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.notification.queue}")
    public void receiveMessage(Message message) {
        String body = new String(message.getBody());
        String routingKey = message.getMessageProperties().getReceivedRoutingKey();

        System.out.println("📩 Received message: " + body);
        System.out.println("🔑 Routing key used: " + routingKey);

        //send email to vukkumsaiprakash@gmail.com
        emailService.sendSimpleMail("vukkumsaiprakash@gmail.com", body, body);
    }
}