package edu.keepeasy.restaurant_companion.domain;

public class OrderItem extends Entity {
    private long mealID;
    private long orderID;
    private int quantity;

    public OrderItem(long id, long mealID, long orderID, int quantity) {
        super(id);
        this.mealID = mealID;
        this.orderID = orderID;
        this.quantity = quantity;
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
}
