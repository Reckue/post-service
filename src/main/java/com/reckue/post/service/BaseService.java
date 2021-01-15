package com.reckue.post.service;

import java.util.List;

/**
 * Interface AuthService represents a common base service with CRUD operations for services
 * with authorization.
 *
 * @author Kamila Meshcheryakova
 */
public interface BaseService<T> {

    T create(T object);

    T update(T object);

    List<T> findAll();

    List<T> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    T findById(String id);

    void deleteById(String id);

}
