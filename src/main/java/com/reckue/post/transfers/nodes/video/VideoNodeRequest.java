package com.reckue.post.transfers.nodes.video;

import com.fasterxml.jackson.annotation.JsonCreator;
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
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class VideoNodeRequest extends NodeParentRequest {

    @NotNull
    private String videoUrl;

    @JsonCreator
    public VideoNodeRequest(@NotNull String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
