package com.reckue.post.transfer;

import com.reckue.post.model.type.NodeType;
import com.reckue.post.model.type.ParentType;
import com.reckue.post.model.type.StatusType;
import com.reckue.post.transfer.node.NodeParentResponse;
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
public class NodeResponse {

    @ApiModelProperty(notes = "The database generated node ID")
    private String id;

    @ApiModelProperty(notes = "Node type")
    private NodeType type;

    @ApiModelProperty(notes = "Sub node")
    private NodeParentResponse node;

    @ApiModelProperty(notes = "Type of parent entity")
    @NotNull
    private ParentType parentType;

    @ApiModelProperty(notes = "Identifier of the parent")
    @NotNull
    private String parentId;

    @Size(max = 128)
    @ApiModelProperty(notes = "The source used to write the node")
    private String source;

    @ApiModelProperty(notes = "Node author identifier")
    private String userId;

    @ApiModelProperty(notes = "Created date")
    private long createdDate;

    @ApiModelProperty(notes = "Modification date")
    private long modificationDate;

    @ApiModelProperty(notes = "Node activity status")
    private StatusType status;
}
