package com.reckue.post.transfers;

import com.reckue.post.models.Comment;
import com.reckue.post.models.Node;
import com.reckue.post.models.NodeType;
import com.reckue.post.models.StatusType;
import com.reckue.post.utils.NodeContent;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @ApiModelProperty(notes = "id")
    private String userId;
    @NotNull
    @ApiModelProperty(notes = "id")
    private String postId;
    @ApiModelProperty(notes = "List of comments in the current comment")
    private List<Comment> comments;
}
