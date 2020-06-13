package com.reckue.post.utils.converters;

import com.reckue.post.models.Node;
import com.reckue.post.transfers.NodeRequest;
import com.reckue.post.transfers.NodeResponse;

/**
 * Class for converting NodeRequest object to Node and Node object to NodeResponse.
 *
 * @author Viktor Grigoriev
 */
public class NodeConverter {

    /**
     * Converts from NodeRequest to Node.
     *
     * @param nodeRequest the object of class NodeRequest
     * @return the object of class Node
     */
    public static Node convert(NodeRequest nodeRequest) {
        if (nodeRequest == null) {
            throw new IllegalArgumentException("Null parameters are not allowed");
        }
        return Node.builder()
                .username(nodeRequest.getUsername())
                .content(nodeRequest.getContent())
                .source(nodeRequest.getSource())
                .type(nodeRequest.getType())
                .status(nodeRequest.getStatus())
                .published(nodeRequest.getPublished())
                .build();
    }

    /**
     * Converts from Node to NodeResponse.
     *
     * @param node the object of class Node
     * @return the object of class NodeResponse
     */
    public static NodeResponse convert(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Null parameters are not allowed");
        }
        return NodeResponse.builder()
                .id(node.getId())
                .content(node.getContent())
                .username(node.getUsername())
                .source(node.getSource())
                .type(node.getType())
                .status(node.getStatus())
                .published(node.getPublished())
                .build();
    }
}
