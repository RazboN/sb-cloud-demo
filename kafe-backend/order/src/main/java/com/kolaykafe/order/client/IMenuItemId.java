package com.kolaykafe.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "menu-service", url = "http://localhost:1001/api/query/menu")
public interface IMenuItemId {
    @PostMapping("/items")
    Long getItemNameById(@RequestBody String itemName);
}
