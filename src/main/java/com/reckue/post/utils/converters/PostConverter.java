package com.reckue.post.utils.converters;

import com.reckue.post.models.Post;
import com.reckue.post.transfers.PostRequest;
import com.reckue.post.transfers.PostResponse;

/**
 * Class PostConverter converts from PostRequest object to PostResponse
 *
 * @author Kamila Meshcheryakova
 */
public class PostConverter {

    /**
     * This method is used to convert from the object of class PostRequest
     * to the object of class Post
     *
     * @param postRequest the object of class PostRequest
     * @return the object of class Post
     */
    public static Post convert(PostRequest postRequest) {
        if (postRequest == null) throw new IllegalArgumentException("Null parameters are not allowed");
        return Post.builder()
                .title(postRequest.getTitle())
                .username(postRequest.getUsername())
                .nodes(postRequest.getNodes())
                .source(postRequest.getSource())
                .tags(postRequest.getTags())
                .published(postRequest.getPublished())
                .changed(postRequest.getChanged())
                .status(postRequest.getStatus())
                .build();
    }

    /**
     * This method is used to convert from the object of class Post
     * to the object of class PostResponse
     *
     * @param post the object of class Post
     * @return the object of class PostResponse
     */
    public static PostResponse convert(Post post) {
        if (post == null) throw new IllegalArgumentException("Null parameters are not allowed");
        return PostResponse.builder()
                .id(post.getId())
                .username(post.getUsername())
                .title(post.getTitle())
                .nodes(post.getNodes())
                .source(post.getSource())
                .tags(post.getTags())
                .published(post.getPublished())
                .changed(post.getChanged())
                .status(post.getStatus())
                .build();
    }
}
