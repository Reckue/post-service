package com.reckue.post.transfers;

import com.reckue.post.models.types.NodeType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class NodeRequest represents an incoming DTO for adding a node.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@Builder
public class NodeRequest<T> {

    @NotNull
    @ApiModelProperty(notes = "Type of node content")
    private NodeType type;

    @ApiModelProperty(notes = "Type of node content")
    @NotNull
    private T node;

    @ApiModelProperty(notes = "Identifier of the post")
    @NotNull
    private String postId;

    @ApiModelProperty(notes = "The source used to write the node")
    private String source;

    @ApiModelProperty(notes = "Node author identifier")
    private String userId;

    @ApiModelProperty(notes = "Publication date")
    private long published;
}
