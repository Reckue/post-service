package com.reckue.post.services;

import com.reckue.post.models.TextNode;

import java.util.List;

/**
 * Interface TextNodeService represents service with CRUD operations.
 *
 * @author Daria Smirnova
 */
public interface TextNodeService {

    /**
     * This method is used to create an object of class TextNode.
     *
     * @param textNode object of class TextNode
     * @return textNode object of class TextNode
     */
    TextNode create(TextNode textNode);

    /**
     * This method is used to update data in an object of class TextNode.
     *
     * @param textNode object of class TextNode
     * @return textNode object of class TextNode
     */
    TextNode update(TextNode textNode);

    /**
     * This method is used to get all objects of class TextNode by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class Node
     */
    List<TextNode> findAll(int limit, int offset, String sort, boolean desc);

    /**
     * This method is used to get an object by id.
     *
     * @param id object
     * @return object of class TextNode
     */
    TextNode findById(String id);

    /**
     * This method is used to delete an object by id.
     *
     * @param id object
     */
    void deleteById(String id);
}
