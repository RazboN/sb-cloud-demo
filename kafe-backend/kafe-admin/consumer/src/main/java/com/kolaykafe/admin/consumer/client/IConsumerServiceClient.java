package com.kolaykafe.admin.consumer.client;

import com.kolaykafe.admin.consumer.dto.ItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "${app.request-service-name}", url = "${app.save-request}")
public interface IConsumerServiceClient {
    @PostMapping
    ResponseEntity<ItemDTO> addItemToMenuServiceDb(@RequestBody ItemDTO item);
}


