package com.reckue.post.transfer.node.image;

import com.reckue.post.transfer.node.NodeParentResponse;
import lombok.*;

/**
 * Class ImageNodeResponse represents an outgoing DTO for receiving type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageNodeResponse implements NodeParentResponse {

    private String imageUrl;
}
