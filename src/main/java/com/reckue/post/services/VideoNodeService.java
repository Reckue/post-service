package com.reckue.post.services;

import com.reckue.post.models.VideoNode;

import java.util.List;

/**
 * Interface VideoNodeService represents service with CRUD operations.
 *
 * @author Viktor Grigoriev
 */
public interface VideoNodeService {

    /**
     * This method is used to create an object of class Node.
     * @param videoNode object of class VideoNode
     * @return videoNode object of class VideoNode
     */
    VideoNode create(VideoNode videoNode);

    /**
     * This method is used to update data in an object of class VideoNode.
     * @param videoNode object of class VideoNode
     * @return videoNode object of class VideoNode
     */
    VideoNode update(VideoNode videoNode);

    /**
     * This method is used to get all objects of class VideoNode by parameters.
     * @param limit quantity of objects
     * @param offset quantity to skip
     * @param sort parameter for sorting
     * @param desc sorting descending
     * @return list of objects of class VideoNode
     */
    List<VideoNode> findAll(int limit, int offset, String sort, boolean desc);

    /**
     * This method is used to get an object by id.
     * @param id object
     * @return object of class VideoNode
     */
    VideoNode findById(String id);

    /**
     * This method is used to delete an object by id.
     * @param id object
     */
    void deleteById(String id);
}
