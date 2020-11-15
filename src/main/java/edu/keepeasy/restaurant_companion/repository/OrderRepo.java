package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.util.ArrayList;

@org.springframework.stereotype.Repository
public class OrderRepo implements Repository<Order> {
    private static final String insertQuery = "INSERT INTO orders(waiterID, tableID, orderStatus) value (?,?,?)";
    private static final String selectAllQuery = "SELECT id, waiterID, tableID, orderStatus FROM orders";
    private static final String selectByIDQuery = "SELECT id, waiterID, tableID, orderStatus FROM orders where id=?";
    private static final String updateQuery = "UPDATE orders SET id=?, waiterID=?, tableID=?, orderStatus=?";
    private static final String deleteQuery = "DELETE FROM orders where id=?";
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Order create(Order entity) {
        Object[] args = new Object[]{
                entity.getWaiterID(),
                entity.getTableID(),
                entity.isOrderStatus()};
        int[] argTypes = new int[]{
                Types.BIGINT,
                Types.INTEGER,
                Types.BOOLEAN};
        return getResult(
                jdbcOperations.queryForRowSet(
                        insertQuery,
                        args,
                        argTypes));
    }

    @Override
    public Order[] readAll() {
        ArrayList<Order> orders = new ArrayList<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectAllQuery);
        Order order = getResult(rowSet);
        while (order != null) {
            orders.add(order);
            order = getResult(rowSet);
        }
        return orders.toArray(new Order[0]);
    }

    @Override
    public Order read(long id) {
        return getResult(
                jdbcOperations.queryForRowSet(
                        selectByIDQuery,
                        new Object[]{id},
                        new int[]{Types.BIGINT}));
    }

    @Override
    public Order update(long id, Order entity) {
        Object[] args = new Object[]{
                id,
                entity.getWaiterID(),
                entity.getTableID(),
                entity.isOrderStatus()};
        int[] argTypes = new int[]{
                Types.BIGINT,
                Types.BIGINT,
                Types.INTEGER,
                Types.BOOLEAN};
        return getResult(
                jdbcOperations.queryForRowSet(
                        updateQuery,
                        args,
                        argTypes));
    }

    @Override
    public Order delete(Order entity) {
        return getResult(
                jdbcOperations.queryForRowSet(
                        deleteQuery,
                        new Object[]{entity.getId()},
                        new int[]{Types.BIGINT}));
    }

    private Order getResult(SqlRowSet rowSet) {
        if (rowSet.next()) {
            return new Order(
                    rowSet.getLong("id"),
                    rowSet.getLong("waiterID"),
                    rowSet.getInt("tableID"),
                    rowSet.getBoolean("orderStatus"));
        } else {
            return null;
        }
    }
}
