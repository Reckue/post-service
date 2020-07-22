package com.reckue.post.transfers.nodes.video;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
