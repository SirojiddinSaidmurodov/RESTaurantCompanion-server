package edu.keepeasy.restaurant_companion.domain;

public class Order extends Entity {
    private long waiterID;
    private int tableID;
    private boolean orderStatus;
    private OrderItem[] orderItems;

    public Order(long id, long waiterID, int tableID, boolean orderStatus, OrderItem[] orderItems) {
        super(id);
        this.waiterID = waiterID;
        this.tableID = tableID;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }

    public OrderItem[] getMeals() {
        return orderItems;
    }

    public void setMeals(OrderItem[] orderItems) {
        this.orderItems = orderItems;
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

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }
}
