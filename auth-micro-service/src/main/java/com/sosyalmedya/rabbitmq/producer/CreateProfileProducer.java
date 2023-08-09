package com.sosyalmedya.rabbitmq.producer;

import com.sosyalmedya.rabbitmq.model.CreateProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProfileProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendCreateProfileMessage(CreateProfile createProfile){
        rabbitTemplate.convertAndSend("direct-exchange-auth","routing-key-auth-create-profile",createProfile);
    }
}
