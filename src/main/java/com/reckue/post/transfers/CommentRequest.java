package com.reckue.post.transfers;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Class CommentRequest represents an incoming DTO for adding a comment.
 *
 * @author Artur Magomedov
 */
@Data
@Builder
public class CommentRequest {

    @NotNull
    @ApiModelProperty(notes = "User id")
    private String userId;

    @NotNull
    @ApiModelProperty(notes = "Post id")
    private String postId;

    @ApiModelProperty(notes = "Сomment id that is being addressed")
    private String commentId;

    @ApiModelProperty(notes = "List of nodes the comment consists of")
    @Valid
    private List<CommentNodeRequest> nodes;
}
