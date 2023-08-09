package com.kolaykafe.userproducer.service;

import com.kolaykafe.userproducer.dto.UserDTO;
import com.kolaykafe.userproducer.service.interfaces.IUserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserCommandServiceImp implements IUserCommandService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    public UserDTO registerUser(UserDTO obj) throws RuntimeException{
        CompletableFuture<SendResult<String, Object>> msgResult =
                kafkaTemplate.send("register", obj);

        msgResult.whenComplete((res,ex) -> {
            if(ex == null) {
                //message sent succesfuly
            }
            else {
                throw new RuntimeException("Message from Kafka: {0}", ex.getCause());
            }
        });

        return obj;
    }

    @Override
    public UserDTO updateUser(UserDTO obj) {
        CompletableFuture<SendResult<String, Object>> msgResult =
            kafkaTemplate.send("update", obj);

        msgResult.whenComplete((res,ex) -> {
            if(ex == null) {
                //message sent succesfuly
            }
            else {
                throw new RuntimeException("Message from Kafka: {0}", ex.getCause());
            }
        });

        return obj;
    }

    @Override
    public boolean verifyEmail(String email) {
        UserDTO temp = new UserDTO();
        temp.setEmail(email);
        CompletableFuture<SendResult<String, Object>> msgResult =
            kafkaTemplate.send("verify-email", temp);

        msgResult.whenComplete((res,ex) -> {
            if(ex == null) {
                //message sent succesfuly
            }
            else {
                throw new RuntimeException("Message from Kafka: {0}", ex.getCause());
            }
        });

        return true;
    }
}
