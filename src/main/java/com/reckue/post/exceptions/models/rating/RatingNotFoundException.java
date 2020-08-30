package com.reckue.post.exceptions.models.rating;

import com.reckue.post.exceptions.ModelNotFoundException;
import lombok.Getter;

/**
 * Class RatingNotFoundException is responsible for throwing
 * exception when the received Rating is not found in the database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class RatingNotFoundException extends ModelNotFoundException {

    private final String message;

    public RatingNotFoundException() {
        this.message = "Rating Not Found";
    }

    public RatingNotFoundException(String id) {
        this.message = "Rating by id '" + id + "' is not found";
    }
}
