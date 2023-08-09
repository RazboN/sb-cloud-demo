package com.kolaykafe.userproducer.service;

import com.kolaykafe.userproducer.dto.UserDTO;
import com.kolaykafe.userproducer.model.User;
import com.kolaykafe.userproducer.repository.IUserRepository;
import com.kolaykafe.userproducer.service.interfaces.IUserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaCommandListener implements IUserCommandService {
    @Autowired
    private IUserRepository _repo;

    @Override
    @KafkaListener(topics = "register", groupId = "user-service-group")
    public UserDTO registerUser(@Payload UserDTO obj, Acknowledgment ack) {
        User newUser = new User();
        newUser.setFullName(obj.getFullName());
        newUser.setPassword(obj.getPassword());
        newUser.setEmail(obj.getEmail());
        newUser.setPhoneNumber(obj.getPhoneNumber());
        newUser.setMailVerified(false);

        _repo.save(newUser);
        ack.acknowledge();
        return obj;
    }

    @Override
    @KafkaListener(topics = "update", groupId = "user-service-group")
    public UserDTO updateUser(@Payload UserDTO obj, Acknowledgment ack) {
        User recentUser = _repo.fingByEmail(obj.getEmail());

        recentUser.setFullName(obj.getFullName());
        recentUser.setPassword(obj.getPassword());
        recentUser.setEmail(obj.getEmail());
        recentUser.setPhoneNumber(obj.getPhoneNumber());
        recentUser.setMailVerified(obj.getMailVerified());

        _repo.save(recentUser);
        ack.acknowledge();
        return obj;
    }

    @Override
    @KafkaListener(topics = "verify-email", groupId = "user-service-group")
    public boolean verifyEmail(@Payload String email, Acknowledgment ack) {
        User verifiedMail = _repo.fingByEmail(email);
        verifiedMail.setMailVerified(true);

        _repo.save(verifiedMail);
        ack.acknowledge();
        return true;
    }
}
