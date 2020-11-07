package com.reckue.post.transfer;

import com.reckue.post.model.type.NodeType;
import com.reckue.post.model.type.ParentType;
import com.reckue.post.transfer.node.NodeParentRequest;
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

    @ApiModelProperty(notes = "Type of parent entity")
    @NotNull
    private ParentType parentType;

    @ApiModelProperty(notes = "Identifier of the parent")
    @NotNull
    private String parentId;

    @ApiModelProperty(notes = "The source used to write the node")
    private String source;

}
