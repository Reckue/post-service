package com.reckue.post.transfer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Class TagResponse represents an outgoing DTO for receiving a tag.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@Builder
public class TagResponse {

    @ApiModelProperty(notes = "Database generated tag ID")
    private String id;

    @ApiModelProperty(notes = "Tag name")
    private String name;
}
