package com.reckue.post.utils.converters;

import com.reckue.post.models.Node;
import com.reckue.post.models.Post;
import com.reckue.post.transfers.NodeRequest;
import com.reckue.post.transfers.NodeResponse;
import com.reckue.post.transfers.PostRequest;
import com.reckue.post.transfers.PostResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Class PostConverter converts from PostRequest object to Post and Post object to PostResponse.
 *
 * @author Kamila Meshcheryakova
 */
public class PostConverter {

    /**
     * This method is used to convert from the object of class PostRequest
     * to the object of class Post.
     *
     * @param postRequest the object of class PostRequest
     * @return the object of class Post
     */
    public static Post convert(PostRequest postRequest) {
        if (postRequest == null) {
            throw new IllegalArgumentException("Null parameters are not allowed");
        }

        List<Node> nodes = new ArrayList<>();
        for (NodeRequest node : postRequest.getNodes()) {
            nodes.add(NodeConverter.convert(node));
        }

        return Post.builder()
                .title(postRequest.getTitle())
                .userId(postRequest.getUserId())
                .nodes(nodes)
                .source(postRequest.getSource())
                .tags(postRequest.getTags())
                .published(postRequest.getPublished())
                .changed(postRequest.getChanged())
                .status(postRequest.getStatus())
                .build();
    }

    /**
     * This method is used to convert from the object of class Post
     * to the object of class PostResponse.
     *
     * @param post the object of class Post
     * @return the object of class PostResponse
     */
    public static PostResponse convert(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Null parameters are not allowed");
        }

        List<NodeResponse> nodes = new ArrayList<>();
        for (Node node : post.getNodes()) {
            nodes.add(NodeConverter.convert(node));
        }

        return PostResponse.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .nodes(nodes)
                .source(post.getSource())
                .tags(post.getTags())
                .published(post.getPublished())
                .changed(post.getChanged())
                .status(post.getStatus())
                .build();
    }
}
