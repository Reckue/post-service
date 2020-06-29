package com.reckue.post.utils.converters;

import com.reckue.post.models.Rating;
import com.reckue.post.transfers.RatingRequest;
import com.reckue.post.transfers.RatingResponse;

/**
 * Class for converting RatingRequest object to Rating and Rating object to RatingResponse.
 *
 * @author Iveri Narozashvili
 */
public class RatingConverter {
    /**
     * Converts from RatingRequest to Rating.
     *
     * @param ratingRequest the object of class RatingRequest
     * @return the object of class Rating
     */
    public static Rating convert(RatingRequest ratingRequest) {
        if (ratingRequest == null) {
            throw new IllegalArgumentException("Null parameters are not allowed");
        }
        return Rating.builder()
                .postId(ratingRequest.getPostId())
                .userId(ratingRequest.getUserId())
                .id(ratingRequest.getId())
                .build();
    }

    /**
     * Converts from Rating to RatingResponse.
     *
     * @param rating the object of class Rating
     * @return the object of class RatingResponse
     */
    public static RatingResponse convert(Rating rating) {
        if (rating == null) {
            throw new IllegalArgumentException("Null parameters are not allowed");
        }
        return RatingResponse.builder()
                .postId(rating.getPostId())
                .userId(rating.getUserId())
                .id(rating.getId())
                .build();
    }
}

