package com.reckue.post.exception.model.rating;

import com.reckue.libs.exception.ReckueException;
import lombok.Getter;

/**
 * Class RatingNotFoundException is responsible for throwing
 * exception when the received Rating is not found in the database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class RatingNotFoundException extends ReckueException {

    private final String message;

    public RatingNotFoundException() {
        this.message = "Rating Not Found";
    }

    public RatingNotFoundException(String id) {
        this.message = "Rating by id '" + id + "' is not found";
    }
}
