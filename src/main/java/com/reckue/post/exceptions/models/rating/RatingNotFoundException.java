package com.reckue.post.exceptions.models.rating;

import com.reckue.post.exceptions.ModelNotFoundException;

public class RatingNotFoundException extends ModelNotFoundException {

    private final String message;

    public RatingNotFoundException() {
        this.message = "Rating Not Found";
    }

    public RatingNotFoundException(String id) {
        this.message = "Rating by id " + id + " is not found";
    }
}
