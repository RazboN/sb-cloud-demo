package com.kolaykafe.userproducer.service.interfaces;

import com.kolaykafe.userproducer.dto.UserDTO;

import java.util.concurrent.ExecutionException;

public interface IUserCommandService {
    UserDTO registerUser(UserDTO obj) throws ExecutionException, InterruptedException;
    UserDTO updateUser(UserDTO obj);
    boolean verifyEmail(String email);
}
