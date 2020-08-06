package com.reckue.post.controllers;

import com.reckue.post.controllers.apis.DebugApi;
import com.reckue.post.models.Node;
import com.reckue.post.models.Post;
import com.reckue.post.models.Rating;
import com.reckue.post.services.BaseService;
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
@RequestMapping(value = "/debug")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DebugController implements DebugApi {

    private final BaseService<Node> nodeService;
    private final BaseService<Post> postService;
    private final BaseService<Rating> ratingService;

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

    /**
     * This method is used to delete all ratings.
     */
    @Deprecated
    @GetMapping("/deleteAllRatings")
    @Override
    public void deleteAllRatings() { ratingService.deleteAll();
    }
}
