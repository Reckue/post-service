package com.reckue.post.transfers.commentNodes.list;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.CommentNodeType;
import com.reckue.post.transfers.commentNodes.CommentNodeParentRequest;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Class CommentListNodeRequest represents an incoming DTO for adding type of comment node such as a list of contents.
 *
 * @author Artur Magomedov
 */
@Builder
@Data
public class CommentListNodeRequest implements CommentNodeParentRequest {

    @NotNull
    private List<String> content;

    @NotNull
    private CommentNodeType type;

    @JsonCreator
    public CommentListNodeRequest(@NotNull List<String> content,
                                  @NotNull CommentNodeType type) {
        this.content = content;
        this.type = type;
    }
}
