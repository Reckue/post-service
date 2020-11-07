package com.reckue.post.transfer;

import com.reckue.post.model.Tag;
import com.reckue.post.model.type.PostStatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
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
    @Valid
    private List<NodeRequest> nodes;

    @Size(max = 128)
    @ApiModelProperty(notes = "The source used to write the post")
    private String source;

    @ApiModelProperty(notes = "List of keywords used in the post")
    private List<Tag> tags;

    @ApiModelProperty(notes = "Post activity status")
    private PostStatusType status;
}
