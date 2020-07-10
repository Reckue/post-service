package com.reckue.post.transfers.nodes.audio;

import com.fasterxml.jackson.annotation.JsonCreator;
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
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class AudioNodeRequest extends NodeParentRequest {

    @NotNull
    private String audioUrl;

    @JsonCreator
    public AudioNodeRequest(@NotNull String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
