package com.reckue.post.utils.converters;

import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.models.Node;
import com.reckue.post.transfers.NodeRequest;
import com.reckue.post.transfers.NodeResponse;
import com.reckue.post.transfers.nodes.NodeParentResponse;

import java.time.ZoneId;

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
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }

        return Node.builder()
                .type(nodeRequest.getType())
                .parentId(nodeRequest.getParentId())
                .userId(nodeRequest.getUserId())
                .source(nodeRequest.getSource())
                .parentType(nodeRequest.getParentType())
                .node(Converter.convert(nodeRequest.getNode(), nodeRequest.getType().nodeClass))
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
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }

        return NodeResponse.builder()
                .id(node.getId())
                .type(node.getType())
                .parentId(node.getParentId())
                .source(node.getSource())
                .userId(node.getUserId())
                .parentType(node.getParentType())
                .createdDate(node.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .modificationDate(node.getModificationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .node((NodeParentResponse) Converter.convert(node.getNode(), node.getType().nodeClass))
                .status(node.getStatus())
                .build();
    }
}
