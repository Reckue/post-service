package com.reckue.post.services;

import com.reckue.post.models.Post;

import java.util.List;

/**
 * Interface PostService represents service with CRUD operations.
 *
 * @author Kamila Meshcheryakova
 */
public interface PostService {

    /**
     * This method is used to create an object of class Post.
     *
     * @param post object of class Post
     * @return post object of class Post
     */
    Post create(Post post);

    /**
     * This method is used to update data in an object of class Post.
     *
     * @param post object of class Post
     * @return post object of class Post
     */
    Post update(Post post);

    /**
     * This method is used to get all objects of class Post.
     *
     * @return list of objects of class Post
     */
    List<Post> findAll();

    /**
     * This method is used to get all objects of class Post by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class Post
     */
    List<Post> findAll(int limit, int offset, String sort, boolean desc);

    /**
     * This method is used to get an object by id.
     *
     * @param id object
     * @return post object of class Post
     */
    Post findById(String id);

    /**
     * This method is used to delete an object by id.
     *
     * @param id object
     */
    void deleteById(String id);
}
