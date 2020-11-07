package com.reckue.post.service;

import com.reckue.post.model.Post;

import java.util.List;

/**
 * Interface PostService extends base auth interface with CRUD-operations and add own methods.
 *
 * @author Kamila Meshcheryakova
 */
public interface PostService extends AuthService<Post> {

    /**
     * This method is used to get the objects by title.
     *
     * @param title object
     * @return list of objects of class Post
     */
    List<Post> findAllByTitle(String title);
}
