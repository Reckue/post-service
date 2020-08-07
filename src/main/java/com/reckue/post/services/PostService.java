package com.reckue.post.services;

import com.reckue.post.models.Post;

import java.util.List;

/**
 * Interface PostService extends base interface with CRUD-operations and add own methods.
 *
 * @author Kamila Meshcheryakova
 */
public interface PostService extends BaseService<Post> {

    /**
     * This method is used to get the objects by title.
     *
     * @param title object
     * @return list of objects of class Post
     */
    List<Post> findAllByTitle(String title);

    /**
     * This method is used to delete all posts.
     */
    @Deprecated
    void deleteAll();
}
