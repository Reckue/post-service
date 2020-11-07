package com.reckue.post.transfer.node.audio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.model.type.NodeType;
import com.reckue.post.transfer.node.NodeParentRequest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class AudioNodeRequest represents an incoming DTO for adding type of node such as an audio.
 *
 * @author Daria Smirnova
 */
@Builder
@Data
public class AudioNodeRequest implements NodeParentRequest {

    @NotNull
    private String audioUrl;

    @NotNull
    private NodeType type;

    @JsonCreator
    public AudioNodeRequest(@NotNull String audioUrl,
                            @NotNull NodeType type) {
        this.audioUrl = audioUrl;
        this.type = type;
    }
}
