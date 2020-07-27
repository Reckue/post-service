package com.reckue.post.transfers.nodes.video;

import com.reckue.post.transfers.nodes.NodeParentResponse;
import lombok.*;

/**
 * Class VideoNodeResponse represents an outgoing DTO for receiving type of node such as a video.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class VideoNodeResponse implements NodeParentResponse {

    private String videoUrl;
}
