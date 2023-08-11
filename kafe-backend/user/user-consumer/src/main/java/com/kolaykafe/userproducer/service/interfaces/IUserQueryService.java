package com.kolaykafe.userproducer.service.interfaces;

import com.kolaykafe.userproducer.dto.UserDTO;

import java.util.stream.Stream;

public interface IUserQueryService {
    Stream<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String email);
}
