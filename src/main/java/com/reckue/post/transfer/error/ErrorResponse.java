package com.reckue.post.transfer.error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

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
