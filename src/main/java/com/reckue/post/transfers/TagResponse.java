package com.reckue.post.transfers;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagResponse {

    @ApiModelProperty(notes = "Database generated tag ID")
    private String id;

    @ApiModelProperty(notes = "Tag name")
    private String name;
}
