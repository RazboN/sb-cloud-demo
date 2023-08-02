package com.kolaykafe.kafebackend.menu.controller;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;
import com.kolaykafe.kafebackend.menu.model.client.OrderItemsDTO;
import com.kolaykafe.kafebackend.menu.service.MenuQueryServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/query/menu")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "false")
@Slf4j
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

    @PostMapping("/items")
    public Long getMenuItemIdByName(@NotNull @RequestBody List<OrderItemsDTO> orderItems) {
        orderItems.forEach(itm -> log.info("item name: {0}", itm.getItemName()));
        return null;//_service.getMenuItemIdByName(itemName);
    }
}
