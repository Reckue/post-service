package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VideoNodeRequest implements NodeContent {

    @NotNull
    private String videoUrl;
}
