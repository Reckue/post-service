package com.reckue.post.transfers.nodes.video;

import com.reckue.post.utils.NodeWrapper;
import lombok.Data;

/**
 * Class VideoNodeResponse represents an outgoing DTO for receiving type of node such as a video.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class VideoNodeResponse {

    private String videoUrl;
}
