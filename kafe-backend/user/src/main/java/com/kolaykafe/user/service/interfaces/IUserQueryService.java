package com.kolaykafe.user.service.interfaces;

import com.kolaykafe.user.dto.UserDTO;

import java.util.stream.Stream;

public interface IUserQueryService {
    Stream<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String email);
}
