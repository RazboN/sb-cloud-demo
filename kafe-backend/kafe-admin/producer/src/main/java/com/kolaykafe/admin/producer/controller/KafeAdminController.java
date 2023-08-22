package com.kolaykafe.admin.producer.controller;

import com.kolaykafe.admin.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class KafeAdminController {
    @Autowired
    private ProducerService _service;

    @PostMapping("/addItems")
    public ResponseEntity<Boolean> addItemsFromExcel(@RequestParam("menuFile") MultipartFile menuFile) {
        try {
            _service.readMenuFile(menuFile);
            return ResponseEntity.ok(true);
        }catch (Exception ex) {
            return ResponseEntity.internalServerError().body(false);
        }
    }
}
