package com.reckue.post.repository;

import com.reckue.post.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface RatingRepository is responsible for connecting to MongoDB.
 *
 * @author Kamila Meshcheryakova
 */
@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    /**
     * This method is used to check the existing of rating to that post by that user.
     *
     * @param userId the user identifier
     * @param postId the post identifier
     * @return true or false
     */
    boolean existsByUserIdAndPostId(String userId, String postId);

    /**
     * This method is used to find the rating to that post by that user.
     *
     * @param userId the user identifier
     * @param postId the post identifier
     * @return true or false
     */
    Rating findByUserIdAndPostId(String userId, String postId);

    /**
     * This method is used to get all ratings to a single post.
     *
     * @param postId the post identifier
     * @return list of objects of class Rating
     */
    List<Rating> findByPostId(String postId);

    /**
     * This method is used to get all ratings by the user.
     *
     * @param userId the user identifier
     * @return list of objects of class Rating
     */
    List<Rating> findByUserId(String userId);

    /**
     * This method is used to check the existing of rating to that post.
     *
     * @param postId the post identifier
     * @return true or false
     */
    boolean existsByPostId(String postId);

    /**
     * This method is used to check the existing of rating by that user.
     *
     * @param userId the post identifier
     * @return true or false
     */
    boolean existsByUserId(String userId);

    /**
     * This method is used to get all ratings by user id.
     *
     * @param userId the user identifier
     * @return list of objects of class Rating
     */
    List<Rating> findAllByUserId(String userId);
}
