package com.kolaykafe.admin.consumer.client;

import com.kolaykafe.admin.consumer.dto.ItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "menu-service", url = "http://localhost:1001/api/command/menu")
public interface IConsumerServiceClient {
    @PostMapping
    ResponseEntity<ItemDTO> addItemToMenuServiceDb(@RequestBody ItemDTO item);
}


