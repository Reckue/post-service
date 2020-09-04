package com.reckue.post.transfers.commentNodes.text;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.CommentNodeType;
import com.reckue.post.transfers.commentNodes.CommentNodeParentRequest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class CommentTextNodeRequest represents an incoming DTO
 * for adding type of comment node such as a text.
 *
 * @author Artur Magomedov
 */
@Builder
@Data
public class CommentTextNodeRequest implements CommentNodeParentRequest {

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    private CommentNodeType type;

    @JsonCreator
    public CommentTextNodeRequest(@NotNull String content,
                                  CommentNodeType type) {
        this.content = content;
        this.type = type;
    }
}
