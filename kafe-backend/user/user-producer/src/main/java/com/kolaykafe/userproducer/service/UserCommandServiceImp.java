package com.kolaykafe.userproducer.service;

import com.kolaykafe.userproducer.dto.UserDTO;
import com.kolaykafe.userproducer.service.interfaces.IUserCommandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class UserCommandServiceImp implements IUserCommandService {
    @Autowired
    private KafkaProducer<String, Object> kafkaProducer;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    public UserDTO registerUser(UserDTO obj) throws RuntimeException, ExecutionException, InterruptedException {
        int retVal = 1;
        ProducerRecord<String,Object> producerRec = new ProducerRecord<>("register","key1", obj);
        kafkaProducer.send(producerRec, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception == null) {
                    log.info("callback: " + metadata.toString());
                }
                else {
                    log.error("Con not produce message. " + exception);
                    throw new RuntimeException("Message can not be produced.");
                }
            }
        }).get();

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
