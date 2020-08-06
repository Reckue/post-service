package com.reckue.post.services;

import java.util.List;

/**
 * Interface BaseService represents a common base service with CRUD operations for all objects.
 *
 * @author Kamila Meshcheryakova
 */
public interface BaseService<T> {

    /**
     * This method is used to create an object of desired class.
     *
     * @param object of desired class
     * @return object of desired class
     */
    T create(T object);

    /**
     * This method is used to update data in an object of desired class.
     *
     * @param object of desired class
     * @return object of desired class
     */
    T update(T object);

    /**
     * This method is used to get all objects of desired class.
     *
     * @return list of objects of desired class
     */
    List<T> findAll();

    /**
     * This method is used to get all objects of desired class by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of desired class
     */
    List<T> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    /**
     * This method is used to get an object by id.
     *
     * @param id of object
     * @return object of desired class
     */
    T findById(String id);

    /**
     * This method is used to delete an object by id.
     *
     * @param id object
     */
    void deleteById(String id);

    /**
     * This method is used to get the objects by title.
     *
     * @param title object
     * @return list of objects of desired class
     */
    default List<T> findAllByTitle(String title) {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to delete all objects of desired class.
     */
    @Deprecated
    default void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
