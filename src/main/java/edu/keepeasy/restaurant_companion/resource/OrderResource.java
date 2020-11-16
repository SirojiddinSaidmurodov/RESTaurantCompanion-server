package edu.keepeasy.restaurant_companion.resource;

import edu.keepeasy.restaurant_companion.domain.Order;

public class OrderResource extends Resource {
    private long id;
    private long waiterID;
    private int tableID;
    private boolean ready;

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
}
