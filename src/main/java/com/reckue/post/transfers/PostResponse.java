package com.reckue.post.transfers;

import com.reckue.post.models.Tag;
import com.reckue.post.models.types.StatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class PostResponse represents an outgoing DTO for receiving a post.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@Builder
public class PostResponse {

    @ApiModelProperty(notes = "Database generated post ID")
    private String id;

    @ApiModelProperty(notes = "Post title")
    private String title;

    @ApiModelProperty(notes = "List of nodes the post consists of")
    private List<NodeResponse> nodes;

    @ApiModelProperty(notes = "The source used to write the post")
    private String source;

    @ApiModelProperty(notes = "Post author id")
    private String userId;

    @ApiModelProperty(notes = "List of keywords used in the post")
    private List<Tag> tags;

    @ApiModelProperty(notes = "Publication date")
    private LocalDateTime createdDate;

    @ApiModelProperty(notes = "Date of changes")
    private LocalDateTime modificationDate;

    @ApiModelProperty(notes = "Post activity status")
    private StatusType status;
}
