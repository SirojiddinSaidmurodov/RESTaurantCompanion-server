package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@org.springframework.stereotype.Repository
public class OrderRepo implements Repository<Order> {
    private static final String selectAllQuery = "SELECT id, waiterID, tableID, ready FROM orders";
    private static final String selectByIDQuery = "SELECT id, waiterID, tableID, ready FROM orders where id=?";
    private static final String updateQuery = "UPDATE orders SET id=?, waiterID=?, tableID=?, ready=? where id=?";
    private static final String deleteQuery = "DELETE FROM orders where id=?";
    @Autowired
    private JdbcOperations jdbcOperations;
    @Autowired
    private JdbcTemplate template;

    @Override
    public Order create(Order entity) {
        long id = Objects.requireNonNull(new SimpleJdbcInsert(template)
                .withTableName("orders")
                .usingColumns("waiterID", "tableID", "ready")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKeyHolder(Map.of(
                        "waiterID", entity.getWaiterID(),
                        "tableID", entity.getTableID(),
                        "ready", entity.isReady()))
                .getKey()).longValue();
        entity.setId(id);
        return entity;
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
                entity.isReady(),
                id};
        int[] argTypes = new int[]{
                Types.BIGINT,
                Types.BIGINT,
                Types.INTEGER,
                Types.BOOLEAN,
                Types.BIGINT};
        int rows = jdbcOperations.update(
                        updateQuery,
                        args,
                        argTypes);
        if (rows == 0){
            return null;
        }else{
            entity.setId(id);
            return entity;
        }
    }

    @Override
    public Order delete(Order entity) {
        int rows = jdbcOperations.update(
                        deleteQuery,
                        new Object[]{entity.getId()},
                        new int[]{Types.BIGINT});
        if (rows == 0){
            return null;
        }else{
            return entity;
        }
    }

    private Order getResult(SqlRowSet rowSet) {
        if (rowSet.next()) {
            return new Order(
                    rowSet.getLong("id"),
                    rowSet.getLong("waiterID"),
                    rowSet.getInt("tableID"),
                    rowSet.getBoolean("ready"));
        } else {
            return null;
        }
    }
}
