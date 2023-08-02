package com.kolaykafe.order.service;

import com.kolaykafe.order.client.IMenuItemId;
import com.kolaykafe.order.dto.OrderDTO;
import com.kolaykafe.order.dto.OrderItemsDTO;
import com.kolaykafe.order.model.OrderDetails;
import com.kolaykafe.order.repository.IOrderRepository;
import com.kolaykafe.order.service.interfaces.IOrderCommandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCommandServiceImp implements IOrderCommandService {

    @Autowired
    private IMenuItemId _menuItemClient;
    @Autowired
    private IOrderRepository _repository;

    @Override
    public OrderDTO saveAndUpdateOrder(OrderDTO order) {
        List<OrderItemsDTO> oItems = order.getOrderItems();

        List<OrderDetails> oDetails = _menuItemClient.getItemNameById(oItems);
        return order;
    }
}
