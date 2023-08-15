package com.kolaykafe.userproducer.service;

import com.kolaykafe.userproducer.dto.UserDTO;
import com.kolaykafe.userproducer.service.interfaces.IUserQueryService;
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
import java.util.concurrent.Future;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserQueryServiceImp implements IUserQueryService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private KafkaProducer<String,Object>kafkaProducer;
    @Override
    public Stream<UserDTO> getAllUsers() throws ExecutionException, InterruptedException {
        Stream<UserDTO> stUsers = null;

        UserDTO tempData = new UserDTO("can",
                "123","aa","bb",true);

        ProducerRecord<String,Object> producerRec = new ProducerRecord<>("get-all","key1",tempData);
        kafkaProducer.send(producerRec, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception == null) {
                    log.info("callback: " + metadata.toString());
                }
                else {
                    log.error("Con not produce message. " + exception);
                }
            }
        }).get();
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
