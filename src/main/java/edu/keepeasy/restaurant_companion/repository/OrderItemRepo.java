package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Meal;

public class OrderItemRepo implements Repository<Meal> {
    @Override
    public Meal create(Meal entity) {
        return null;
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
    public Meal update(long id, Meal entity) {
        return null;
    }

    @Override
    public Meal delete(Meal entity) {
        return null;
    }
}
