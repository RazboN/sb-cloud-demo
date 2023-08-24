package com.kolaykafe.admin.consumer.service;

import com.kolaykafe.admin.consumer.client.IConsumerServiceClient;
import com.kolaykafe.admin.consumer.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @Autowired
    private IConsumerServiceClient _menuClient;
    public boolean addItem(ItemDTO item) {
        try {
            _menuClient.addItemToMenuServiceDb(item);
            return true;
        } catch (Exception ex) {
            System.out.println("Error on adding to menu db: " + ex.getMessage());
            return false;
        }
    }
}