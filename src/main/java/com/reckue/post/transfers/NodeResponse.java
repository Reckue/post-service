package com.reckue.post.transfers;

import com.reckue.post.models.nodes.Parent;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.models.types.StatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class NodeResponse represents an outgoing DTO for receiving a node.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@Builder
public class NodeResponse<T> {

    @ApiModelProperty(notes = "The database generated node ID")
    private String id;

    @ApiModelProperty(notes = "Node type")
    private NodeType type;

    @ApiModelProperty(notes = "Type of node content")
    private T node;

    @ApiModelProperty(notes = "Identifier of the post")
    @NotNull
    private String postId;

    @Size(max = 128)
    @ApiModelProperty(notes = "The source used to write the node")
    private String source;

    @ApiModelProperty(notes = "Node author identifier")
    private String userId;

    @ApiModelProperty(notes = "Publication date")
    private long published;

    @ApiModelProperty(notes = "Node activity status")
    private StatusType status;
}
