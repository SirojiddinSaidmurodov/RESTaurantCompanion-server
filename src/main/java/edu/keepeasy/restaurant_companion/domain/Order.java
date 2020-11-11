package edu.keepeasy.restaurant_companion.domain;

public class Order extends Entity {
    private long waiterID;
    private int tableID;
    private boolean orderStatus;
    private Meal[] meals;

    public Order(long id, long waiterID, int tableID, boolean orderStatus, Meal[] meals) {
        super(id);
        this.waiterID = waiterID;
        this.tableID = tableID;
        this.orderStatus = orderStatus;
        this.meals = meals;
    }

    public Meal[] getMeals() {
        return meals;
    }

    public void setMeals(Meal[] meals) {
        this.meals = meals;
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
