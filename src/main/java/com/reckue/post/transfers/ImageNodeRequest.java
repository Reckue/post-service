package com.reckue.post.transfers;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ImageNodeRequest {

    @NotNull
    private String imageUrl;
}
