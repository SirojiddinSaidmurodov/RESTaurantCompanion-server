package edu.keepeasy.restaurant_companion.resource;

import edu.keepeasy.restaurant_companion.domain.OrderItem;

public class OrderItemResource extends Resource {
    private long id;
    private long mealID;
    private long orderID;
    private int quantity;

    public OrderItemResource() {

    }

    public OrderItemResource(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.mealID = orderItem.getMealID();
        this.orderID = orderItem.getOrderID();
        this.quantity = orderItem.getQuantity();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMealID() {
        return mealID;
    }

    public void setMealID(long mealID) {
        this.mealID = mealID;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItem toEntity() {
        return new OrderItem(
                id,
                mealID,
                orderID,
                quantity);
    }
}
