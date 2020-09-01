package com.reckue.post.transfers;

import com.reckue.post.models.types.CommentNodeType;
import com.reckue.post.transfers.commentNodes.CommentNodeParentRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class CommentNodeRequest represents an incoming DTO for adding a comment node.
 *
 * @author Artur Magomedov
 */
@Data
@Builder
public class CommentNodeRequest {

    @NotNull
    @ApiModelProperty(notes = "Type of comment node content")
    private CommentNodeType type;

    @ApiModelProperty(notes = "Sub node")
    private CommentNodeParentRequest node;

    @ApiModelProperty(notes = "The source used to write the node")
    private String source;

    @ApiModelProperty(notes = "Identifier of the comment")
    private String commentId;
}
