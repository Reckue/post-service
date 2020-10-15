package com.reckue.post.controllers;

import com.reckue.post.controllers.apis.DebugApi;
import com.reckue.post.services.CommentService;
import com.reckue.post.services.NodeService;
import com.reckue.post.services.PostService;
import com.reckue.post.services.RatingService;
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

    private final NodeService nodeService;
    private final PostService postService;
    private final RatingService ratingService;
    private final CommentService commentService;

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
    public void deleteAllRatings() {
        ratingService.deleteAll();
    }

    /**
     * This method is used to delete all comments.
     */
    @Deprecated
    @GetMapping("/deleteAllComments")
    @Override
    public void deleteAllComments() {
        commentService.deleteAll();
    }
}
