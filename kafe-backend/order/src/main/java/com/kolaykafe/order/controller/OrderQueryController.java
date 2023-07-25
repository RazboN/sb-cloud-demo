package com.kolaykafe.order.controller;

import com.kolaykafe.order.dto.OrderDTO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "false")
@RequestMapping("/api/order")
public class OrderQueryController {

    @GetMapping("/{cafeId}/{orderId}/{tableNo}")
    public String getOrderOfTable(@RequestParam int cafeId, @RequestParam long orderId,
                                            @RequestParam int tableNo){
        //Stream<OrderDTO>
        return "hoooop";
    }
}
