package edu.keepeasy.restaurant_companion.domain;

public class Order extends Entity {
    private long waiterID;
    private int tableID;
    private boolean ready;

    public Order(long id, long waiterID, int tableID, boolean ready) {
        super(id);
        this.waiterID = waiterID;
        this.tableID = tableID;
        this.ready = ready;
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
}
