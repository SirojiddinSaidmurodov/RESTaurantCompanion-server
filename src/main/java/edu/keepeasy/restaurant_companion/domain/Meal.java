package edu.keepeasy.restaurant_companion.domain;

public class Meal extends Entity {
    private String mealName;
    private int mealCost;
    private boolean mealAvailable;

    public Meal(long id, String mealName, int mealCost, boolean mealAvailable) {
        super(id);
        this.mealName = mealName;
        this.mealCost = mealCost;
        this.mealAvailable = mealAvailable;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getMealCost() {
        return mealCost;
    }

    public void setMealCost(int mealCost) {
        this.mealCost = mealCost;
    }

    public boolean isMealAvailable() {
        return mealAvailable;
    }

    public void setMealAvailable(boolean mealAvailable) {
        this.mealAvailable = mealAvailable;
    }
}
