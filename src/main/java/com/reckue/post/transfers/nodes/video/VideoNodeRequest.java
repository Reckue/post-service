package com.reckue.post.transfers.nodes.video;

import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Class VideoNodeRequest represents an incoming DTO for adding type of node such as a video.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class VideoNodeRequest extends NodeParentRequest {

    @NotNull
    private String videoUrl;
}
