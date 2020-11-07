package com.reckue.post.exception;

import lombok.Getter;

@Getter
public class ReckueUnauthorizedException extends ReckueException {

    private final String message;

    public ReckueUnauthorizedException(String message) {
        this.message = message;
    }
}
