package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Order;

public class OrderRepo implements Repository<Order> {
    @Override
    public void create(Order entity) {

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
    public void update(long id, Order entity) {

    }

    @Override
    public void delete(Order entity) {

    }
}
