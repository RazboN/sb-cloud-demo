package com.kolaykafe.user.service.interfaces;

import com.kolaykafe.user.dto.UserDTO;
import com.kolaykafe.user.model.User;

public interface IUserCommandService {
    UserDTO registerUser(UserDTO obj);
    UserDTO updateUser(UserDTO obj);
    boolean verifyEmail(String email);
}
