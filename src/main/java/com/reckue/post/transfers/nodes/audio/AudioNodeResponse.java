package com.reckue.post.transfers.nodes.audio;

import com.reckue.post.transfers.nodes.NodeParentResponse;
import lombok.*;

/**
 * Class AudioNodeResponse represents an outgoing DTO for receiving type of node such as an audio.
 *
 * @author Daria Smirnova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AudioNodeResponse implements NodeParentResponse {

    private String audioUrl;
}
