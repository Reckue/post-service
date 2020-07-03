package com.reckue.post.transfers;

import com.reckue.post.models.Node;
import com.reckue.post.models.Tag;
import com.reckue.post.models.types.StatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Class PostRequest represents an incoming DTO for adding a post.
 *
 * @author Kamila Meshcheryakova
 */
@Builder
@Data
public class PostRequest {

    @NotNull
    @ApiModelProperty(notes = "Post title")
    private String title;

    @ApiModelProperty(notes = "List of nodes the post consists of")
    private List<Node<?>> nodes;

    @Size(max = 128)
    @ApiModelProperty(notes = "The source used to write the post")
    private String source;

    @ApiModelProperty(notes = "Post author id")
    private String userId;

    @ApiModelProperty(notes = "List of keywords used in the post")
    private List<Tag> tags;

    @ApiModelProperty(notes = "Publication date")
    private long published;

    @ApiModelProperty(notes = "Date of changes")
    private long changed;

    @NotNull
    @ApiModelProperty(notes = "Post activity status")
    private StatusType status;
}
