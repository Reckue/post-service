package com.reckue.post.transfer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Class PostRatingResponse represents an outgoing DTO for receiving a count of ratings to a concrete post.
 *
 * @author Kamila Meshcheryakova
 * created 10.07.2020
 */
@Data
@Builder
public class PostRatingResponse {

    @ApiModelProperty(notes = "Count of ratings to post")
    private int count;
}
