package com.reckue.post.service;

import com.reckue.post.model.Post;
import com.reckue.post.model.Rating;

import java.util.List;

/**
 * Interface RatingService extends base auth interface with CRUD-operations and add own methods.
 *
 * @author Kamila Meshcheryakova
 */
public interface RatingService extends BaseService<Rating> {

    default Rating findById(String id) {
        throw new UnsupportedOperationException();
    }

    int getRatingsCountByPostId(String postId);

    List<Post> findAllPostsWithRatingsByUserId(String userId, Integer limit, Integer offset);

}
