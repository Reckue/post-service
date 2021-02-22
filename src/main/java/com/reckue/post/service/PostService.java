package com.reckue.post.service;

import com.reckue.post.model.Post;

import java.util.List;

/**
 * Interface PostService extends base auth interface with CRUD-operations and add own methods.
 *
 * @author Kamila Meshcheryakova
 */
public interface PostService extends BaseService<Post> {

    List<Post> findAllByTitle(String title);

    List<Post> findAllByUserId(String userId, Integer limit, Integer offset);

}
