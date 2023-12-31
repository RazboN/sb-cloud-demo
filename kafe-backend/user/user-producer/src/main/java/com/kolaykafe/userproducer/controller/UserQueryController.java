package com.kolaykafe.userproducer.controller;

import com.kolaykafe.userproducer.dto.UserDTO;
import com.kolaykafe.userproducer.service.UserQueryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/query/user")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "false")
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
