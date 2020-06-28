package com.reckue.post.transfers.nodes.image;

import com.reckue.post.utils.NodeWrapper;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class ImageNodeRequest represents an incoming DTO for adding type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class ImageNodeRequest {

    @NotNull
    private String imageUrl;
}
