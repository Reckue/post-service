package com.reckue.post.transfers;

import com.reckue.post.models.types.NodeType;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import com.reckue.post.utils.NodeContent;
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
public class NodeRequest {

    @NotNull
    @ApiModelProperty(notes = "Type of node content")
    private NodeType type;

    @ApiModelProperty(notes = "Sub node")
    private NodeParentRequest node;

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
