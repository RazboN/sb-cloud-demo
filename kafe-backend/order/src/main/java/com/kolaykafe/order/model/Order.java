package com.kolaykafe.order.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "ORDERS")
public class
Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private long orderId;

    @Column(name = "CAFE_ID")
    private long cafeId;

    @Column(name = "TABLE_NO")
    private int tableNo;

    @Column(name = "ORDER_ACTIVE")
    private boolean orderActive;

    @Column(name = "PRICE")
    private boolean price;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private Set<OrderDetails> orderDetails;
}
