package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

/**
 * Class ImageNodeResponse represents an outgoing DTO for receiving type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class ImageNodeResponse implements NodeContent {

    private String id;
    private String imageUrl;
}
