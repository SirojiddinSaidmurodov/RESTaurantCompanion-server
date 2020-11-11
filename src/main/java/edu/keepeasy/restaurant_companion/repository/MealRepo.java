package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Meal;

public class MealRepo implements Repository<Meal> {
    @Override
    public void create(Meal entity) {

    }

    @Override
    public Meal[] read(Specification specification) {
        return new Meal[0];
    }

    @Override
    public void update(Meal entity) {

    }

    @Override
    public void delete(Meal entity) {

    }
}
