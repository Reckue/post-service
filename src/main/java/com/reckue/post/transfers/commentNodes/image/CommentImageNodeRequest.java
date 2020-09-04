package com.reckue.post.transfers.commentNodes.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.CommentNodeType;
import com.reckue.post.transfers.commentNodes.CommentNodeParentRequest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class CommentImageNodeRequest represents an incoming DTO for adding type of comment node such as an image.
 *
 * @author Artur Magomedov
 */
@Builder
@Data
public class CommentImageNodeRequest implements CommentNodeParentRequest {

    @NotNull
    private String imageUrl;

    @NotNull
    private CommentNodeType type;

    @JsonCreator
    public CommentImageNodeRequest(@NotNull String imageUrl,
                                   @NotNull CommentNodeType type) {
        this.imageUrl = imageUrl;
        this.type = type;
    }
}
