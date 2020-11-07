package com.reckue.post.transfer.node.video;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.model.type.NodeType;
import com.reckue.post.transfer.node.NodeParentRequest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class VideoNodeRequest represents an incoming DTO for adding type of node such as a video.
 *
 * @author Kamila Meshcheryakova
 */
@Builder
@Data
public class VideoNodeRequest implements NodeParentRequest {

    @NotNull
    private String videoUrl;

    @NotNull
    private NodeType type;

    @JsonCreator
    public VideoNodeRequest(@NotNull String videoUrl,
                            @NotNull NodeType type) {
        this.videoUrl = videoUrl;
        this.type = type;
    }
}
