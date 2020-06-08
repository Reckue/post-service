package com.reckue.post.transfers;

import com.reckue.post.models.Node;
import com.reckue.post.models.StatusType;
import com.reckue.post.models.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostResponse {

    @ApiModelProperty(notes = "The database generated post ID")
    private String id;

    @ApiModelProperty(notes = "The post title")
    private String title;

    @ApiModelProperty(notes = "List of nodes the post consists of")
    private List<Node> nodes;

    @ApiModelProperty(notes = "The source used to write the post")
    private String source;

    @ApiModelProperty(notes = "The post's author")
    private String username;

    @ApiModelProperty(notes = "List of keywords used in the post")
    private List<Tag> tags;

    @ApiModelProperty(notes = "Publication date")
    private long published;

    @ApiModelProperty(notes = "Date of changes")
    private long changed;

    @ApiModelProperty(notes = "Post activity status")
    private StatusType status;
}
