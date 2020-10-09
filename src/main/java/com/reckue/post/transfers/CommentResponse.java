package com.reckue.post.transfers;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Class CommentResponse represents an outgoing DTO for receiving a comment.
 *
 * @author Artur Magomedov
 */
@Data
@Builder
public class CommentResponse {

    @ApiModelProperty(notes = "Database generated comment ID")
    private String id;

    @NotNull
    @ApiModelProperty(notes = "User id")
    private String userId;

    @NotNull
    @ApiModelProperty(notes = "Post id")
    private String postId;

    @ApiModelProperty(notes = "Сomment id that is being addressed")
    private String commentId;

    @ApiModelProperty(notes = "Created date")
    private long createdDate;

    @ApiModelProperty(notes = "Modification date")
    private long modificationDate;

    @ApiModelProperty(notes = "List of nodes the comment consists of")
    private List<NodeResponse> nodes;
}
