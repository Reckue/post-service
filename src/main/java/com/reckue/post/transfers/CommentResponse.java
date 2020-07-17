package com.reckue.post.transfers;

import com.reckue.post.models.Comment;
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
    @ApiModelProperty(notes = "Text of comment")
    private String text;

    @NotNull
    @ApiModelProperty(notes = "User id")
    private String userId;

    @NotNull
    @ApiModelProperty(notes = "Post id")
    private String postId;

    @ApiModelProperty(notes = "Created date")
    private long createdDate;

    @ApiModelProperty(notes = "Modification date")
    private long modificationDate;

    @ApiModelProperty(notes = "List of comments in the current comment")
    private List<Comment> comments;
}
