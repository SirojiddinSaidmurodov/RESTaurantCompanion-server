package edu.keepeasy.restaurant_companion.resource;

import edu.keepeasy.restaurant_companion.domain.Meal;

public class MealResource extends Resource {
    private long id;
    private String mealName;
    private int mealCost;
    private boolean mealAvailable;

    public MealResource() {
    }

    public MealResource(Meal meal) {
        this.id = meal.getId();
        this.mealName = meal.getMealName();
        this.mealCost = meal.getMealCost();
        this.mealAvailable = meal.isMealAvailable();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Meal toEntity() {
        return new Meal(id,
                mealName,
                mealCost,
                mealAvailable);
    }
}
