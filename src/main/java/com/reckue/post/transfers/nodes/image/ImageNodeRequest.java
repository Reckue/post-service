package com.reckue.post.transfers.nodes.image;

import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Class ImageNodeRequest represents an incoming DTO for adding type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageNodeRequest extends NodeParentRequest {

    @NotNull
    private String imageUrl;
}
