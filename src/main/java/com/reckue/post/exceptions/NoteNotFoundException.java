package com.reckue.post.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoteNotFoundException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public NoteNotFoundException(String message, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
