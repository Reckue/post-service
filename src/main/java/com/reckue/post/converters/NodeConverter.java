package com.reckue.post.converters;

import com.reckue.post.models.Node;
import com.reckue.post.transfers.NodeRequest;
import com.reckue.post.transfers.NodeResponse;

public class NodeConverter {

    // from request to model
    public static Node convert(NodeRequest nodeRequest) {
        return Node.builder()
                .contentId(nodeRequest.getContentId())
                .source(nodeRequest.getSource())
                .type(nodeRequest.getType())
                .status(nodeRequest.getStatus())
                .published(nodeRequest.getPublished())
                .build();
    }

    // from model to response
    public static NodeResponse convert(Node node) {
        return NodeResponse.builder()
                .id(node.getId())
                .contentId(node.getContentId())
                .source(node.getSource())
                .type(node.getType())
                .status(node.getStatus())
                .published(node.getPublished())
                .build();
    }
}
