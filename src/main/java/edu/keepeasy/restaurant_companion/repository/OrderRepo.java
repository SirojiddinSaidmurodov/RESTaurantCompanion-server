package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Order;

public class OrderRepo implements Repository<Order> {
    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Order[] readAll() {
        return new Order[0];
    }

    @Override
    public Order read(long id) {
        return null;
    }

    @Override
    public Order update(long id, Order entity) {
        return null;
    }

    @Override
    public Order delete(Order entity) {
    return null;
    }
}
