package com.reckue.post.transfers;

import com.reckue.post.models.types.NodeType;
import com.reckue.post.models.types.StatusType;
import com.reckue.post.utils.NodeWrapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class NodeRequest represents an incoming DTO for adding a node.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@Builder
public class NodeRequest<T> {

    @NotNull
    @ApiModelProperty(notes = "Node type")
    private NodeType type;

    @ApiModelProperty(notes = "Type of node content")
    private T node;

    @Size(max=128)
    @ApiModelProperty(notes = "The source used to write the node")
    private String source;

    @ApiModelProperty(notes = "Node author")
    private String username;

    @ApiModelProperty(notes = "Publication date")
    private long published;

    @NotNull
    @ApiModelProperty(notes = "Node activity status")
    private StatusType status;
}
