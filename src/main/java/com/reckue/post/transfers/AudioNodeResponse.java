package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

/**
 * Class AudioNodeResponse represents an outgoing DTO for receiving type of node such as an audio.
 *
 * @author Daria Smirnova
 */
@Data
public class AudioNodeResponse implements NodeContent {

    private String audioUrl;
}
