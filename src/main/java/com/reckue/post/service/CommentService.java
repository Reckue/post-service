package com.reckue.post.service;

import com.reckue.post.model.Comment;

import java.util.List;

/**
 * Interface CommentService extends base auth interface with CRUD-operations and add own methods.
 *
 * @author Artur Magomedov
 */
public interface CommentService extends BaseService<Comment> {

    List<Comment> findAllByUserId(String userId, Integer limit, Integer offset);

}
