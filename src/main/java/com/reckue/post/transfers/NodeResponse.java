package com.reckue.post.transfers;

import com.reckue.post.models.NodeType;
import com.reckue.post.models.StatusType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NodeResponse {

    private String id;
    private NodeType type;
    private String contentId;
    private String source;
    private StatusType status;
    private long published;
}
