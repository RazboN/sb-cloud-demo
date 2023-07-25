package com.kolaykafe.order.repository;

import com.kolaykafe.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    /**
     * TODO:
     *      tarih aralığına göre sipariş getirecek ekleme yapılacak
     *      aktif sipariş getirecek ekleme yapılacak
     * */
}
