package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Order;
import edu.keepeasy.restaurant_companion.domain.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
public class OrderItemRepo {
    @Autowired
    JdbcOperations jdbcOperations;
    @Autowired
    JdbcTemplate template;

    public OrderItem create(OrderItem entity) {
        long id = Objects.requireNonNull(new SimpleJdbcInsert(template)
                .withTableName("orderitems")
                .usingColumns("mealID", "orderID", "quantity")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKeyHolder(Map.of(
                        "mealID", entity.getMealID(),
                        "orderID", entity.getOrderID(),
                        "quantity", entity.getQuantity()
                )).getKey()).longValue();
        entity.setId(id);
        return entity;
    }

    public OrderItem[] readAll(Order order) {
        return new OrderItem[0];
    }

    public OrderItem read(Order order, long id) {
        return null;
    }

    public OrderItem update(Order order, long id, OrderItem entity) {
        return null;
    }

    public OrderItem delete(Order order, OrderItem entity) {
        return null;
    }
}
