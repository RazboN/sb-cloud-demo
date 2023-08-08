package com.kolaykafe.user.service;

import com.kolaykafe.user.dto.UserDTO;
import com.kolaykafe.user.model.User;
import com.kolaykafe.user.repository.IUserRepository;
import com.kolaykafe.user.service.interfaces.IUserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class UserQueryServiceImp implements IUserQueryService {
    @Autowired
    private IUserRepository _repo;
    @Override
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
        User user = _repo.fingByEmail(email);

        UserDTO userDTO = new UserDTO(user.getFullName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getMailVerified());

        return userDTO;
    }
}
