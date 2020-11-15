package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.User;

public class UserRepo implements Repository<User> {

    @Override
    public User create(User entity) {
        return null;
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
    public User update(long id, User entity) {
        return null;
    }

    @Override
    public User delete(User entity) {
        return null;
    }
}
