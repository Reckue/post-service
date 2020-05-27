package com.reckue.post.services;

import com.reckue.post.models.ImageNode;

import java.util.List;

/**
 * Interface ImageNodeService represents service with CRUD operations.
 *
 * @author Daria Smirnova
 */
public interface ImageNodeService {

    /**
     * This method is used to create an object of class ImageNode.
     *
     * @param imageNode object of class ImageNode
     * @return imageNode object of class ImageNode
     */
    ImageNode create(ImageNode imageNode);

    /**
     * This method is used to update data in an object of class ImageNode.
     *
     * @param imageNode object of class ImageNode
     * @return imageNode object of class ImageNode
     */
    ImageNode update(ImageNode imageNode);

    /**
     * This method is used to get all objects of class ImageNode.
     *
     * @return list of objects of class ImageNode
     */
    List<ImageNode> findAll();

    /**
     * This method is used to get all objects of class ImageNode by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class ImageNode
     */

    List<ImageNode> findAll(int limit, int offset, String sort, boolean desc);

    /**
     * This method is used to get an object by id.
     *
     * @param id object
     * @return object of class ImageNode
     */
    ImageNode findById(String id);

    /**
     * This method is used to delete an object by id.
     *
     * @param id object
     */
    void deleteById(String id);
}
