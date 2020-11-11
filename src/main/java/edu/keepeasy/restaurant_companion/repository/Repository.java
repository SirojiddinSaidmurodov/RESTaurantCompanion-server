package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Entity;

public interface Repository<T extends Entity> {
    void save(T entity);

    T[] read(Specification specification);

    void update(T entity);

    void delete(T entity);
}
