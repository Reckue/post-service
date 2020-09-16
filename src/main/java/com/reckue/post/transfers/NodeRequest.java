package com.reckue.post.transfers;

import com.reckue.post.models.types.NodeType;
import com.reckue.post.models.types.ParentType;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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

    @ApiModelProperty(notes = "Node author identifier")
    private String userId;
}
