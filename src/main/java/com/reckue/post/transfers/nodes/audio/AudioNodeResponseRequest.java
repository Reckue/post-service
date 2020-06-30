package com.reckue.post.transfers.nodes.audio;

import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class AudioNodeResponse represents an outgoing DTO for receiving type of node such as an audio.
 *
 * @author Daria Smirnova
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AudioNodeResponseRequest extends NodeParentRequest {

    private String audioUrl;
}
