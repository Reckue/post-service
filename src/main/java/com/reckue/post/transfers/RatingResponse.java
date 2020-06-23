package com.reckue.post.transfers;

import com.reckue.post.models.Node;
import com.reckue.post.models.Rating;
import com.reckue.post.models.StatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Class RatingResponse represents an outgoing DTO for receiving a post.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
public class RatingResponse {
    @ApiModelProperty(notes = "Database generated post ID")
    private String id;

    @ApiModelProperty(notes = "Rating title")
    private String title;

    @ApiModelProperty(notes = "List of nodes the post consists of")
    private List<Node> nodes;

    @ApiModelProperty(notes = "The source used to write the post")
    private String source;

    @ApiModelProperty(notes = "Post author")
    private String username;

    @ApiModelProperty(notes = "List of keywords used in the post")
    private List<Rating> tags;

    @ApiModelProperty(notes = "Publication date")
    private long published;

    @ApiModelProperty(notes = "Date of changes")
    private long changed;

    @ApiModelProperty(notes = "Post activity status")
    private StatusType status;
}
