package com.reckue.post.exceptions;

import lombok.Getter;

@Getter
public class ReckueUnauthorizedException extends ReckueException {

    private final String message;

    public ReckueUnauthorizedException(String message) {
        this.message = message;
    }
}
