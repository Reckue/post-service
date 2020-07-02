package com.reckue.post.transfers;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Class RatingRequest represents an incoming DTO for adding a rating.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
public class RatingRequest {
    @ApiModelProperty(notes = "User Id")
    private String userId;

    @ApiModelProperty(notes = "Post Id")
    private String postId;
}
