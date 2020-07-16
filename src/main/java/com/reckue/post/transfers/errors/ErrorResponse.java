package com.reckue.post.transfers.errors;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Class ErrorResponse allows to convert a response to a given response when it throws an exception.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@ApiModel
@Builder
public class ErrorResponse {

    @ApiModelProperty(notes = "Error type")
    private String title;

    @ApiModelProperty(notes = "Error message")
    private String message;

    @ApiModelProperty(notes = "Reckue error code")
    private String code;

    @ApiModelProperty(notes = "Stack trace with exception message")
    private String trace;
}
