package com.reckue.post.transfers.nodes.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import com.reckue.post.utils.NodeContent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Class ImageNodeRequest represents an incoming DTO for adding type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@Builder
@Data
public class ImageNodeRequest extends NodeParentRequest {

    @NotNull
    private String imageUrl;

    @JsonCreator
    public ImageNodeRequest(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
