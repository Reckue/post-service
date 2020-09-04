package com.reckue.post.transfers;

import com.reckue.post.models.types.CommentNodeType;
import com.reckue.post.transfers.commentNodes.CommentNodeParentResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class CommentNodeResponse represents an outgoing DTO for receiving a comment node.
 *
 * @author Artur Magomedov
 */
@Data
@Builder
public class CommentNodeResponse {

    @ApiModelProperty(notes = "The database generated node ID")
    private String id;

    @ApiModelProperty(notes = "Node type")
    private CommentNodeType type;

    @ApiModelProperty(notes = "Sub node")
    private CommentNodeParentResponse node;

    @ApiModelProperty(notes = "Identifier of the comment")
    @NotNull
    private String commentId;

    @Size(max = 128)
    @ApiModelProperty(notes = "The source used to write the node")
    private String source;

    @ApiModelProperty(notes = "Created date")
    private long createdDate;

    @ApiModelProperty(notes = "Modification date")
    private long modificationDate;
}
