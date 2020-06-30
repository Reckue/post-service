package com.reckue.post.transfers.nodes.image;

import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class ImageNodeResponse represents an outgoing DTO for receiving type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageNodeResponse extends NodeParentRequest {

    private String imageUrl;
}
