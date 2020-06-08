package com.reckue.post.transfers;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TagRequest {

    @NotNull
    @ApiModelProperty(notes = "Tag name")
    private String name;
}
