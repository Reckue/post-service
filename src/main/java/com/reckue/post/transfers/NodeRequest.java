package com.reckue.post.transfers;

import com.reckue.post.models.NodeType;
import com.reckue.post.models.StatusType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NodeRequest {

    @NotNull
    private NodeType type;

    private String contentId;

    @Size(max=128)
    private String source;

    @NotNull
    private StatusType status;

    @NotNull
    private long published;
}
