package com.reckue.post.transfers;

import com.reckue.post.models.Comment;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Class CommentResponse represents an outgoing DTO for receiving a comment.
 *
 * @author Artur Magomedov
 */
public class CommentResponse {

    @ApiModelProperty(notes = "Database generated tag ID")
    private String id;

    @NotNull
    @ApiModelProperty(notes = "User id")
    private String userId;
    @NotNull
    @ApiModelProperty(notes = "Post id")
    private String postId;
    @ApiModelProperty(notes = "List of comments in the current comment")
    private List<Comment> comments;
}
