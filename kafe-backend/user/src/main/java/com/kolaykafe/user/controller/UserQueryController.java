package com.kolaykafe.user.controller;

import com.kolaykafe.user.dto.UserDTO;
import com.kolaykafe.user.service.UserQueryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/query/user")
public class UserQueryController {
    @Autowired
    private UserQueryServiceImp _service;
    @GetMapping
    public ResponseEntity<Stream<UserDTO>> getUsers() {
        try {
            return ResponseEntity.ok().body(_service.getAllUsers());
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
