package com.reckue.post.transfers;

import com.reckue.post.models.Comment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

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
    @ApiModelProperty(notes = "Text of comment")
    private String text;
    @NotNull
    @ApiModelProperty(notes = "User id")
    private String userId;
    @NotNull
    @ApiModelProperty(notes = "Post id")
    private String postId;
    @ApiModelProperty(notes = "Publication date")
    private long published;
    @ApiModelProperty(notes = "List of comments in the current comment")
    private List<Comment> comments;
}
