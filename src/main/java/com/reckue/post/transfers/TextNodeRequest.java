package com.reckue.post.transfers;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TextNodeRequest {

    @NotNull
    private String content;
}
