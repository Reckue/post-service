package com.reckue.post.services;

import com.reckue.post.models.VideoNode;

import java.util.List;

/**
 * CRUD operations for VideoNode class.
 *
 * @author Viktor
 */
public interface VideoNodeService {

    VideoNode create(VideoNode videoNode);
    VideoNode update(VideoNode videoNode);
    List findAll(int limit, int offset, String sort, boolean desc);
    VideoNode findById(String id);
    void deleteById(String id);
}
