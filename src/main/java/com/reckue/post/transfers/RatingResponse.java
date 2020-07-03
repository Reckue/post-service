package com.reckue.post.transfers;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Class RatingResponse represents an outgoing DTO for receiving a rating.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
public class RatingResponse {

    @ApiModelProperty(notes = "Database generated rating Id")
    private String id;

    @ApiModelProperty(notes = "Used Id")
    private String userId;

    @ApiModelProperty(notes = "Post Id")
    private String postId;
}
