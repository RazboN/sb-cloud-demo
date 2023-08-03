package com.kolaykafe.order.client;

import com.kolaykafe.order.dto.OrderItemsDTO;
import com.kolaykafe.order.model.OrderDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "menu-service", url = "http://localhost:1001/api/query/menu")
public interface IMenuItemId {
    @PostMapping("/items")
    Long getItemNameById(@RequestBody String itemName);
}
