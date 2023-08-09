package com.kolaykafe.userproducer.service.interfaces;

import com.kolaykafe.userproducer.dto.UserDTO;

public interface IUserCommandService {
    UserDTO registerUser(UserDTO obj);
    UserDTO updateUser(UserDTO obj);
    boolean verifyEmail(String email);
}
