package com.kolaykafe.order.service.interfaces;

import com.kolaykafe.order.dto.OrderDTO;

public interface IOrderCommandService {
    OrderDTO saveAndUpdateOrder(OrderDTO order);
}
