package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Order;

public class OrderRepo implements Repository<Order> {
    @Override
    public void save(Order entity) {

    }

    @Override
    public Order[] read(Specification specification) {
        return new Order[0];
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(Order entity) {

    }
}
