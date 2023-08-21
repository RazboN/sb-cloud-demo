package com.kolaykafe.userproducer.service;

import com.kolaykafe.userproducer.dto.UserDTO;
import com.kolaykafe.userproducer.model.User;
import com.kolaykafe.userproducer.repository.IUserRepository;
import com.kolaykafe.userproducer.service.interfaces.IUserCommandService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaCommandListener implements IUserCommandService {
    @Autowired
    private IUserRepository _repo;

    @Override
    @KafkaListener(topics = "register", groupId = "${spring.kafka.consumer.group-id}")
    @Transactional
    public void registerUser(@Payload UserDTO obj) {
        User newUser = new User();
        newUser.setFullName(obj.getFullName());
        newUser.setPassword(obj.getPassword());
        newUser.setEmail(obj.getEmail());
        newUser.setPhoneNumber(obj.getPhoneNumber());
        newUser.setMailVerified(false);

        _repo.save(newUser);
    }

    @Override
    @KafkaListener(topics = "update", groupId = "${spring.kafka.consumer.group-id}")
    public UserDTO updateUser(@Payload UserDTO obj) {
        if(_repo.findByEmail(obj.getEmail()) != null){
            User recentUser = new User(null, obj.getFullName(),
                    obj.getPassword(),obj.getEmail(), obj.getPhoneNumber(), obj.getMailVerified());

            _repo.save(recentUser);
        }

        return obj;
    }

    @Override
    @KafkaListener(topics = "verify_email", groupId = "${spring.kafka.consumer.group-id}")
    public boolean verifyEmail(@Payload String email) {
        User verifiedMail = _repo.findByEmail(email);
        verifiedMail.setMailVerified(true);

        _repo.save(verifiedMail);
        return true;
    }
}
