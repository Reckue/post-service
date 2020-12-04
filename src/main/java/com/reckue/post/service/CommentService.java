package com.reckue.post.service;

import com.reckue.post.model.Comment;

/**
 * Interface CommentService extends base auth interface with CRUD-operations and add own methods.
 *
 * @author Artur Magomedov
 */
public interface CommentService extends BaseService<Comment> {

    /**
     * This method is used to delete all objects.
     */
    @Deprecated
    void deleteAll();
}
