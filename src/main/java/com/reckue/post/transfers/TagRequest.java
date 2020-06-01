package com.reckue.post.transfers;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TagRequest {

    @NotNull
    private String name;
}
