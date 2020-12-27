package edu.keepeasy.restaurant_companion.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.keepeasy.restaurant_companion.domain.Order;

public class OrderResource extends Resource {
    private long id;
    private long waiterID;
    private int tableID;
    private boolean ready;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderItemResource[] items;

    public OrderResource() {
    }

    public OrderResource(Order order) {
        this.id = order.getId();
        this.waiterID = order.getWaiterID();
        this.tableID = order.getTableID();
        this.ready = order.isReady();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWaiterID() {
        return waiterID;
    }

    public void setWaiterID(long waiterID) {
        this.waiterID = waiterID;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public Order toEntity() {
        return new Order(
                id,
                waiterID,
                tableID,
                ready);
    }

    public OrderItemResource[] getItems() {
        return items;
    }

    public void setItems(OrderItemResource[] items) {
        this.items = items;
    }
}
