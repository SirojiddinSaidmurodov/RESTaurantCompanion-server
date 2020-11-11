package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.User;

public class UserRepo implements Repository<User> {

    @Override
    public void create(User entity) {

    }

    @Override
    public User[] read(Specification specification) {
        return new User[0];
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }
}
