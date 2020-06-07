package com.reckue.post.transfers;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Class ErrorResponse allows to convert a response to a given response when it throws an exception.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@ApiModel
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private Integer status;

    public ErrorResponse(String message, HttpStatus httpStatus, Integer status) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.status = status;
    }
}
