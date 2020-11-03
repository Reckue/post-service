package com.reckue.post.services;

import java.util.List;
import java.util.Map;

/**
 * Interface AuthService represents a common base service with CRUD operations for services
 * with authorization.
 *
 * @author Kamila Meshcheryakova
 * created 14.10.2020
 */
public interface AuthService<T> {
    /**
     * This method is used to create an object of desired class.
     *
     * @param object    of desired class
     * @param tokenInfo user token info
     * @return object of desired class
     */
    T create(T object, Map<String, Object> tokenInfo);

    /**
     * This method is used to update data in an object of desired class.
     *
     * @param object    of desired class
     * @param tokenInfo user token info
     * @return object of desired class
     */
    T update(T object, Map<String, Object> tokenInfo);

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
     * This method is used to get a list of objects by user id.
     *
     * @param userId user identificator
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @return list of objects of desired class
     */
    List<T> findAllByUserId(String userId, Integer limit, Integer offset);

    /**
     * This method is used to delete an object by id.
     *
     * @param id        object
     * @param tokenInfo user token info
     */
    void deleteById(String id, Map<String, Object> tokenInfo);

    /**
     * This method is used to delete all objects.
     */
    @Deprecated
    void deleteAll();
}
