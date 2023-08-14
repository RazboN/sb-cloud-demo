package com.kolaykafe.userproducer.service.interfaces;

import com.kolaykafe.userproducer.dto.UserDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.stream.Stream;

public interface IUserQueryService {
    void getAllUsers(ConsumerRecord<String, Object> record);
    UserDTO getUserByEmail(String email);
}
