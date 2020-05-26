package com.reckue.post.services;

import com.reckue.post.models.Node;

import java.util.List;

/**
 * Interface NodeService represents service with CRUD operations.
 *
 * @author Kamila Meshcheryakova
 */
public interface NodeService {

    /**
     * This method is used to create an object of class Node.
     * @param node object of class Node
     * @return node object of class Node
     */
    Node create(Node node);

    /**
     * This method is used to update data in an object of class Node.
     * @param node object of class Node
     * @return node object of class Node
     */
    Node update(Node node);

    /**
     * This method is used to get all objects of class Node.
     * @return list of objects of class Node
     */
    List<Node> findAll();

    /**
     * This method is used to get all objects of class Node by parameters.
     * @param limit quantity of objects
     * @param offset quantity to skip
     * @param sort parameter for sorting
     * @param desc sorting descending
     * @return list of objects of class Node
     */
    List<Node> findAll(int limit, int offset, String sort, boolean desc);

    /**
     * This method is used to get an object by id.
     * @param id object
     * @return object of class Node
     */
    Node findById(String id);

    /**
     * This method is used to delete an object by id.
     * @param id object
     */
    void deleteById(String id);
}
