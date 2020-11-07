package com.reckue.post.service;

import com.reckue.post.model.Post;
import com.reckue.post.model.Rating;

import java.util.List;

/**
 * Interface RatingService extends base auth interface with CRUD-operations and add own methods.
 *
 * @author Kamila Meshcheryakova
 */
public interface RatingService extends AuthService<Rating> {

    /**
     * {@inheritDoc}
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @implSpec This implementation always throws an
     * {@code UnsupportedOperationException}.
     */
    default Rating findById(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to get the number of ratings to a single post.
     *
     * @param postId the post identifier
     * @return quantity of ratings to a post
     */
    int getRatingsCountByPostId(String postId);

    /**
     * This method is used to get all posts with ratings by user id.
     *
     * @param userId the user identifier
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @return list of objects of class Post
     */
    List<Post> findAllPostsWithRatingsByUserId(String userId, Integer limit, Integer offset);
}
