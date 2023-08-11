package com.kolaykafe.userproducer.service;

import com.kolaykafe.userproducer.dto.UserDTO;
import com.kolaykafe.userproducer.model.User;
import com.kolaykafe.userproducer.repository.IUserRepository;
import com.kolaykafe.userproducer.service.interfaces.IUserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class KafkaQueryListener implements IUserQueryService {
    @Autowired
    private IUserRepository _repo;
    @Override
    @KafkaListener(topics = "get-all", groupId = "user-service-group")
    public Stream<UserDTO> getAllUsers() {
        return _repo.findAll().stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setFullName(user.getFullName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTO.setPhoneNumber(user.getPhoneNumber());

            return userDTO;
        });
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
