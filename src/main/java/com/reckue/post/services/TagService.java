package com.reckue.post.services;

import com.reckue.post.models.Tag;

import java.util.List;

/**
 * Interface TagService represents service with CRUD operations.
 *
 * @author Kamila Meshcheryakova
 */
public interface TagService {

    /**
     * This method is used to create an object of class Tag.
     *
     * @param tag object of class Tag
     * @return tag object of class Tag
     */
    Tag create(Tag tag);

    /**
     * This method is used to update data in an object of class Tag.
     *
     * @param tag object of class Tag
     * @return tag object of class Tag
     */
    Tag update(Tag tag);

    /**
     * This method is used to get all objects of class Tag.
     *
     * @return list of objects of class Tag
     */
    List<Tag> findAll();

    /**
     * This method is used to get all objects of class Tag by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class Node
     */

    List<Tag> findAll(int limit, int offset, String sort, boolean desc);

    /**
     * This method is used to get an object by id.
     *
     * @param id object
     * @return object of class Tag
     */
    Tag findById(String id);

    /**
     * This method is used to delete an object by id.
     *
     * @param id object
     */
    void deleteById(String id);
}