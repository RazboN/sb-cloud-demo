package com.kolaykafe.admin.consumer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kolaykafe.admin.consumer.dto.ItemDTO;
import com.kolaykafe.admin.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class KafeAdminController {
    @Autowired
    private ConsumerService _service;

    @KafkaListener(topics = "ADD_MENU", groupId = "${spring.kafka.consumer.group-id}")
    public ResponseEntity<Boolean> addItemsFromExcel(String event) {
        try {
            ItemDTO item = new ObjectMapper().readValue(event, ItemDTO.class);

            _service.addItem(item);
            return ResponseEntity.ok(true);
        }catch (Exception ex) {
            return ResponseEntity.internalServerError().body(false);
        }
    }
}
