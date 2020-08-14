package com.reckue.post.transfers;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class CommentRequest represents an incoming DTO for adding a comment.
 *
 * @author Artur Magomedov
 */
@Data
@Builder
public class CommentRequest {

    @NotNull
    @ApiModelProperty(notes = "Text of comment")
    private String text;

    @NotNull
    @ApiModelProperty(notes = "User id")
    private String userId;

    @NotNull
    @ApiModelProperty(notes = "Post id")
    private String postId;

    @ApiModelProperty(notes = "Сomment id that is being addressed")
    private String commentId;
}
