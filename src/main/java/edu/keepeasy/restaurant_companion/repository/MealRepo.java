package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Meal;

@org.springframework.stereotype.Repository
public class MealRepo implements Repository<Meal> {
    private static final String insertQuery = "INSERT INTO meal (mealName, mealCost, mealAvailable) VALUES (?, ?, ?)";
    private static final String selectQuery = "SELECT id, mealName, mealCost, mealAvailable from meal";
    private static final String updateQuery = "UPDATE meal SET mealName = ?, mealCost = ?, mealAvailable = ? where id = ?";
    private static final String deleteQuery = "DELETE FROM meal where id = ?";

    @Override
    public void create(Meal entity) {

    }

    @Override
    public Meal[] readAll() {
        return new Meal[0];
    }

    @Override
    public Meal read(long id) {
        return null;
    }


    @Override
    public void update(Meal entity) {

    }

    @Override
    public void delete(Meal entity) {

    }
}
