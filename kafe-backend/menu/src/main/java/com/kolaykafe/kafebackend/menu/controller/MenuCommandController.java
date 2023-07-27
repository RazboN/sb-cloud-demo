package com.kolaykafe.kafebackend.menu.controller;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;
import com.kolaykafe.kafebackend.menu.service.MenuCommandServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/command/menu")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "true")
public class MenuCommandController {
    @Autowired
    private MenuCommandServiceImp _service;

    @PostMapping
    public ResponseEntity<MenuDTO> updateMenuItem(@RequestBody MenuDTO item) {
        if(_service.addAndUpdateItemToMenu(item))
            return ResponseEntity.status(HttpStatus.OK).body(item);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
