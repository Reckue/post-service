package com.reckue.post.transfers;

import com.reckue.post.models.Node;
import com.reckue.post.models.StatusType;
import com.reckue.post.models.Tag;
import lombok.Data;

import java.util.List;

@Data
public class PostRequest {

    private String title;
    private List<Node> nodes;
    private String source;
    private List<Tag> tags;
    private StatusType status;
}
