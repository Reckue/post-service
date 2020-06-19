package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class AudioNodeRequest represents an incoming DTO for adding type of node such as an audio.
 *
 * @author Daria Smirnova
 */
@Data
public class AudioNodeRequest implements NodeContent {

    @NotNull
    private String audioUrl;
}
