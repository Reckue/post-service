package com.reckue.post.transfer.node.audio;

import com.reckue.post.transfer.node.NodeParentResponse;
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
