package com.reckue.post.transfers;

import com.reckue.post.models.NodeType;
import com.reckue.post.models.StatusType;
import com.reckue.post.utils.NodeContent;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NodeResponse {

    private String id;
    private NodeType type;
    private NodeContent content;
    private String username;
    private String source;
    private StatusType status;
    private long published;
}
