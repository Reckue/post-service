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

    @ApiModelProperty(notes = "Database generated rating ID")
    private String id;

    @ApiModelProperty(notes = "Database generated used ID")
    private String userId;

    @ApiModelProperty(notes = "Database generates post ID")
    private String postId;
}
