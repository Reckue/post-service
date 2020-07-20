package com.reckue.post.transfers.nodes.image;

import com.reckue.post.transfers.nodes.NodeParentResponse;
import com.reckue.post.utils.NodeContent;
import lombok.*;

/**
 * Class ImageNodeResponse represents an outgoing DTO for receiving type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageNodeResponse extends NodeParentResponse {

    private String imageUrl;
}
