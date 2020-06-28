package com.reckue.post.transfers.nodes.video;

import com.reckue.post.utils.NodeWrapper;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class VideoNodeRequest represents an incoming DTO for adding type of node such as a video.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class VideoNodeRequest {

    @NotNull
    private String videoUrl;
}
