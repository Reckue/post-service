package com.reckue.post.transfers.nodes.image;

import com.reckue.post.utils.NodeWrapper;
import lombok.Data;

/**
 * Class ImageNodeResponse represents an outgoing DTO for receiving type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class ImageNodeResponse {

    private String id;
    private String imageUrl;
}
