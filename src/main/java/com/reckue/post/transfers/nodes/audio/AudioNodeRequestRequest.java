package com.reckue.post.transfers.nodes.audio;

import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class AudioNodeRequest represents an incoming DTO for adding type of node such as an audio.
 *
 * @author Daria Smirnova
 */
@Data
public class AudioNodeRequestRequest extends NodeParentRequest {

    @NotNull
    private String audioUrl;
}
