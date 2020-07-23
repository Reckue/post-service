package com.reckue.post.transfers.nodes.audio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
