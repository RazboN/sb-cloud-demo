package com.kolaykafe.kafebackend.menu.controller;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;
import com.kolaykafe.kafebackend.menu.service.MenuQueryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/query/menu")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "false")
public class MenuQueryController {
    @Autowired
    private MenuQueryServiceImp _service;

    @GetMapping()
    public ResponseEntity<Stream<MenuDTO>> getCafeMenu(@RequestParam Long cafeId) {
        Stream<MenuDTO> res = _service.getMenuByCafeId(cafeId);

        if(res != null)
            return ResponseEntity.status(HttpStatus.OK).body(res);
        else
            return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
