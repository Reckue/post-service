package com.reckue.post.transfers.nodes.image;

import com.reckue.post.transfers.nodes.NodeParentResponse;
import com.reckue.post.utils.NodeContent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class ImageNodeResponse represents an outgoing DTO for receiving type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@Builder
public class ImageNodeResponse extends NodeParentResponse {

    private String imageUrl;
}
