package com.kolaykafe.userproducer.service.interfaces;

import com.kolaykafe.userproducer.dto.UserDTO;

import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public interface IUserQueryService {
    Stream<UserDTO> getAllUsers() throws ExecutionException, InterruptedException;
    UserDTO getUserByEmail(String email);
}
