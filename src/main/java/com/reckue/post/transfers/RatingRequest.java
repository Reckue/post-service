package com.reckue.post.transfers;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class RatingRequest represents an incoming DTO for adding a rating.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
public class RatingRequest {

    @NotNull
    @ApiModelProperty(notes = "Database generated rating Id")
    private String id;

    @ApiModelProperty(notes = "User Id")
    private String userId;

    @ApiModelProperty(notes = "Post Id")
    private String postId;
}
