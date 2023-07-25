package com.kolaykafe.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private int tableNo;
    private long cafeId;
    private List<OrderItemsDTO> orderItems;
}
