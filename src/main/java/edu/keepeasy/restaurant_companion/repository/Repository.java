package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Entity;

public interface Repository<T extends Entity> {
    T create(T entity);

    T[] readAll();

    T read(long id);

    T update(long id, T entity);

    T delete(T entity);
}
