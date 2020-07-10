package com.reckue.post.controllers;

import com.reckue.post.controllers.apis.DataBaseUtilApi;
import com.reckue.post.services.NodeService;
import com.reckue.post.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class DataBaseUtilController is responsible for utility functions.
 *
 * @author Marina Buinevich
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/database/util")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DataBaseUtilController implements DataBaseUtilApi {

    private final NodeService nodeService;
    private final PostService postService;

    /**
     * This method is used to delete all nodes.
     */
    @Deprecated
    @GetMapping("/deleteAllNodes")
    @Override
    public void deleteAllNodes() {
        nodeService.deleteAll();
    }

    /**
     * This method is used to delete all posts.
     */
    @Deprecated
    @GetMapping("/deleteAllPosts")
    @Override
    public void deleteAllPosts() {
        postService.deleteAll();
    }
}
