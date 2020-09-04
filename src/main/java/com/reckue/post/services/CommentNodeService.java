package com.reckue.post.services;

import com.reckue.post.models.CommentNode;

/**
 * Interface CommentNodeService extends base interface with CRUD-operations and add own methods.
 *
 * @author Kamila Meshcheryakova
 */
public interface CommentNodeService extends BaseService<CommentNode> {

    /**
     * This method is used to delete all nodes.
     */
    @Deprecated
    void deleteAll();
}
