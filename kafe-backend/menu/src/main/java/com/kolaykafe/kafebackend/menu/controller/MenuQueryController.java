package com.kolaykafe.kafebackend.menu.controller;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;
import com.kolaykafe.kafebackend.menu.service.MenuQueryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

    @GetMapping("/{cafeId}")
    public Stream<MenuDTO> getCafeMenu(@RequestParam Long cafeId) {
        return _service.getMenuByCafeId(cafeId);
    }
}
