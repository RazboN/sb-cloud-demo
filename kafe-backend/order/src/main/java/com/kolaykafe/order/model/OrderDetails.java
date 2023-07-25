package com.kolaykafe.order.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ORDER_DETAILS")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ITEM_ID")
    private Long itemId;

    @Column(name = "QUANTITY")
    private float quantity;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
