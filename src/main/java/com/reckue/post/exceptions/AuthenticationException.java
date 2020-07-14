package com.reckue.post.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Class AuthenticationException is responsible for throwing
 * exception when the requested data contains authentication errors.
 *
 * @author Kamila Meshcheryakova
 */
@Getter
@Setter
public class AuthenticationException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    /**
     * Constructor with passed parameters as an information about the exception.
     *
     * @param message    information about exception
     * @param httpStatus the description about Http status code
     */
    public AuthenticationException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
