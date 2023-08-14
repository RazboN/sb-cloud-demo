package com.kolaykafe.userproducer.service;

import com.kolaykafe.userproducer.dto.UserDTO;
import com.kolaykafe.userproducer.service.interfaces.IUserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class UserQueryServiceImp implements IUserQueryService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    public Stream<UserDTO> getAllUsers() {
        Stream<UserDTO> stUsers = null;
        /**
         * mesaj gidemedi
         * */

        CompletableFuture<SendResult<String, Object>> msgResult =
                kafkaTemplate.execute(msg -> {
                    return kafkaTemplate.send("get-all", new UserDTO("can",
                            "123","aa","bb",true));
                }).toCompletableFuture();

        //completed olamıyor nedeni bul???
        msgResult.whenComplete((res,ex) -> {
            if(ex == null) {
                msgResult.complete(res);
            }
            else {
                throw new RuntimeException("Message from Kafka: {0}", ex.getCause());
            }
        });
        return null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Stream<UserDTO> stUsers = null;
        CompletableFuture<SendResult<String, Object>> msgResult =
                kafkaTemplate.send("verify-email", email);

        msgResult.whenComplete((res,ex) -> {
            if(ex == null) {
                //message sent succesfuly
            }
            else {
                throw new RuntimeException("Message from Kafka: {0}", ex.getCause());
            }
        });
        return null;
    }
}
