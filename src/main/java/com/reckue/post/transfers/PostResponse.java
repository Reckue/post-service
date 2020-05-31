package com.reckue.post.transfers;

import com.reckue.post.models.Node;
import com.reckue.post.models.StatusType;
import com.reckue.post.models.Tag;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostResponse {

    private String id;
    private String title;
    private List<Node> nodes;
    private String source;
    private List<Tag> tags;
    private long published;
    private long changed;
    private StatusType status;
}
