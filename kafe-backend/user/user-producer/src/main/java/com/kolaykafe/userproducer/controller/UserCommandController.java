package com.kolaykafe.userproducer.controller;

import com.kolaykafe.userproducer.dto.UserDTO;
import com.kolaykafe.userproducer.service.UserCommandServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/command/user")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "true")
public class UserCommandController {
    @Autowired
    private UserCommandServiceImp _service;
    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO obj) {
        try{
            return ResponseEntity.ok().body(_service.registerUser(obj));
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
