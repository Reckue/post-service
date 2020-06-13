package com.reckue.post.transfers;

import com.reckue.post.models.NodeType;
import com.reckue.post.models.StatusType;
import com.reckue.post.utils.NodeContent;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class NodeRequest {

    @NotNull
    @ApiModelProperty(notes = "Node type")
    private NodeType type;

    @ApiModelProperty(notes = "Type of node content")
    private NodeContent content;

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
