package com.kolaykafe.userproducer.service;

import com.kolaykafe.userproducer.dto.UserDTO;
import com.kolaykafe.userproducer.model.User;
import com.kolaykafe.userproducer.repository.IUserRepository;
import com.kolaykafe.userproducer.service.interfaces.IUserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Component
public class KafkaQueryListener implements IUserQueryService {
    @Autowired
    private IUserRepository _repo;
    @Override
    @KafkaListener(topics = "get_all", groupId = "${spring.kafka.consumer.group-id}")
    public void getAllUsers(ConsumerRecord<String, Object> record) {
        /**
         * gönderilen mesaj geliyor
         * */
        log.info("Sent message: " + record.value());
//        return _repo.findAll().stream().map(user -> {
//            UserDTO userDTO = new UserDTO();
//            userDTO.setFullName(user.getFullName());
//            userDTO.setEmail(user.getEmail());
//            userDTO.setPassword(user.getPassword());
//            userDTO.setPhoneNumber(user.getPhoneNumber());
//
//            return userDTO;
//        });
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = _repo.findByEmail(email);

        UserDTO userDTO = new UserDTO(user.getFullName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getMailVerified());

        return userDTO;
    }
}
