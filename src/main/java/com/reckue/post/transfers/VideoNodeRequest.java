package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class VideoNodeRequest represents an incoming DTO for adding type of node such as a video.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class VideoNodeRequest implements NodeContent {

    @NotNull
    private String videoUrl;
}
