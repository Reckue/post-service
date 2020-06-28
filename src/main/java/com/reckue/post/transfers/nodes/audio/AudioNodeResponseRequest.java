package com.reckue.post.transfers.nodes.audio;

import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Data;

/**
 * Class AudioNodeResponse represents an outgoing DTO for receiving type of node such as an audio.
 *
 * @author Daria Smirnova
 */
@Data
public class AudioNodeResponseRequest extends NodeParentRequest {

    private String audioUrl;
}
