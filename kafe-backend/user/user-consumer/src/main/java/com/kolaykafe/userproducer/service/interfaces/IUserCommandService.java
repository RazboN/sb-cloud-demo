package com.kolaykafe.userproducer.service.interfaces;

import com.kolaykafe.userproducer.dto.UserDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface IUserCommandService {
    @KafkaListener(topics = "update", groupId = "user-service-group")
    UserDTO updateUser(@Payload UserDTO obj, Acknowledgment ack);
    @KafkaListener(topics = "verify-email", groupId = "user-service-group")
boolean verifyEmail(@Payload String email, Acknowledgment ack);

    @KafkaListener(topics = "register", groupId = "user-service-group")
    UserDTO registerUser(@Payload UserDTO obj, Acknowledgment ack);
}
