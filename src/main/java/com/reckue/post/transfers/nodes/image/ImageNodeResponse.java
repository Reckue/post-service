package com.reckue.post.transfers.nodes.image;

import com.reckue.post.transfers.nodes.NodeParentResponse;
import lombok.*;

/**
 * Class ImageNodeResponse represents an outgoing DTO for receiving type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class ImageNodeResponse implements NodeParentResponse {

    private String imageUrl;
}
