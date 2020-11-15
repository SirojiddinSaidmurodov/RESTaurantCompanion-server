package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemRepo {

    public OrderItem create(OrderItem entity) {
        return null;
    }

    public OrderItem[] readAll() {
        return new OrderItem[0];
    }

    public OrderItem read(long id) {
        return null;
    }

    public OrderItem update(long id, OrderItem entity) {
        return null;
    }

    public OrderItem delete(OrderItem entity) {
        return null;
    }
}
