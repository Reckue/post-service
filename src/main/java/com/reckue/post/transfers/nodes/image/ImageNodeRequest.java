package com.reckue.post.transfers.nodes.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.transfers.nodes.NodeParentRequest;
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
