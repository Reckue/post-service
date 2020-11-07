package com.reckue.post.transfer.node.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.model.type.NodeType;
import com.reckue.post.transfer.node.NodeParentRequest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class ImageNodeRequest represents an incoming DTO for adding type of node such as an image.
 *
 * @author Kamila Meshcheryakova
 */
@Builder
@Data
public class ImageNodeRequest implements NodeParentRequest {

    @NotNull
    private String imageUrl;

    @NotNull
    private NodeType type;

    @JsonCreator
    public ImageNodeRequest(@NotNull String imageUrl,
                            @NotNull NodeType type) {
        this.imageUrl = imageUrl;
        this.type = type;
    }
}
