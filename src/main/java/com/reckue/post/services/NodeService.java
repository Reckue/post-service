package com.reckue.post.services;

import com.reckue.post.models.Node;

/**
 * Interface NodeService extends base interface with CRUD-operations and add own methods.
 *
 * @author Kamila Meshcheryakova
 */
public interface NodeService extends BaseService<Node> {

    /**
     * This method is used to delete all nodes.
     */
    @Deprecated
    void deleteAll();
}
