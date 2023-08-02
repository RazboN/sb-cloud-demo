package com.kolaykafe.order.controller;

import com.kolaykafe.order.dto.OrderDTO;
import com.kolaykafe.order.service.OrderCommandServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.internalServerError;

@RestController
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "true")
@RequestMapping("/api/command/order")
public class OrderCommandController {
    @Autowired
    private OrderCommandServiceImp _commandService;

    @PostMapping()
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO orderGiven){
        try {
            _commandService.saveAndUpdateOrder(orderGiven);
            return ResponseEntity.ok(null);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}