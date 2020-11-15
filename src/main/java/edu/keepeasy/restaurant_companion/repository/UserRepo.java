package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.User;

public class UserRepo implements Repository<User> {

    @Override
    public void create(User entity) {

    }

    @Override
    public User[] readAll() {
        return new User[0];
    }

    @Override
    public User read(long id) {
        return null;
    }

    @Override
    public void update(long id, User entity) {

    }

    @Override
    public void delete(User entity) {

    }
}
