package com.kolaykafe.order.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "true")
@RequestMapping("/api/order")
public class OrderCommandController {

}