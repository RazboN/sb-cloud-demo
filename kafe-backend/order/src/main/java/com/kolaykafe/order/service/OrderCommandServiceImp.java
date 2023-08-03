package com.kolaykafe.order.service;

import com.kolaykafe.order.client.IMenuItemId;
import com.kolaykafe.order.dto.OrderDTO;
import com.kolaykafe.order.dto.OrderItemsDTO;
import com.kolaykafe.order.model.Order;
import com.kolaykafe.order.model.OrderDetails;
import com.kolaykafe.order.repository.IOrderRepository;
import com.kolaykafe.order.service.interfaces.IOrderCommandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderCommandServiceImp implements IOrderCommandService {

    @Autowired
    private IMenuItemId _menuItemClient;
    @Autowired
    private IOrderRepository _repository;

    @Autowired
    private ModelMapper _mapper;

    @Override
    public OrderDTO saveAndUpdateOrder(OrderDTO order) {
        Order orderModel = _mapper.map(order, Order.class);
        List<OrderItemsDTO> oItems = order.getOrderItems();
        List<OrderDetails> details = new ArrayList<>();
        OrderDetails itemDetail = null;

        for(OrderItemsDTO item : order.getOrderItems()) {
            itemDetail = new OrderDetails();
            itemDetail.setQuantity(item.getQuantity());
            itemDetail.setItemId(_menuItemClient.getItemNameById(item.getItemName()));
        }

        return order;
    }
}
