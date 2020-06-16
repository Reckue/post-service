package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

/**
 * Class VideoNodeResponse represents an outgoing DTO for receiving type of node such as a video.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class VideoNodeResponse implements NodeContent {

    private String videoUrl;
}
