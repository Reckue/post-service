package com.reckue.post.transfers;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Error message")
    private String message;

    @ApiModelProperty(notes = "Error status")
    private HttpStatus httpStatus;

    @ApiModelProperty(notes = "Error status code")
    private Integer status;

    public ErrorResponse(String message, HttpStatus httpStatus, Integer status) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.status = status;
    }
}
