package com.reckue.post.transfers.nodes.audio;

import com.reckue.post.transfers.nodes.NodeParentRequest;
import com.reckue.post.transfers.nodes.NodeParentResponse;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class AudioNodeResponse represents an outgoing DTO for receiving type of node such as an audio.
 *
 * @author Daria Smirnova
 */
@Data
@Builder
public class AudioNodeResponse extends NodeParentResponse {

    private String audioUrl;
}
