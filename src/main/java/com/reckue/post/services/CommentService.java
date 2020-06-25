package com.reckue.post.services;

import com.reckue.post.models.Comment;

import java.util.List;

/**
 * Interface CommentService represents service with CRUD operations.
 *
 * @author Artur Magomedov
 */
public interface CommentService {

    /**
     * This method is used to create an object of class Comment.
     *
     * @param comment object of class Comment
     * @return comment object of class Comment
     */
    Comment create(Comment comment);

    /**
     * This method is used to update data in an object of class Comment.
     *
     * @param comment object of class Comment
     * @return comment object of class Comment
     */
    Comment update(Comment comment);

    /**
     * This method is used to get all objects of class Comment.
     *
     * @return list of objects of class Comment
     */
    List<Comment> findAll();

    /**
     * This method is used to get all objects of class Comment by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class Node
     */

    List<Comment> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    /**
     * This method is used to get an object by id.
     *
     * @param id object
     * @return object of class Comment
     */
    Comment findById(String id);

    /**
     * This method is used to delete an object by id.
     *
     * @param id object
     */
    void deleteById(String id);
}
