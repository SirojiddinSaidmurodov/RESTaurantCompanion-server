package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Meal;

public class OrderItemRepo implements Repository<Meal> {
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
    public void update(long id, Meal entity) {

    }

    @Override
    public void delete(Meal entity) {

    }
}
