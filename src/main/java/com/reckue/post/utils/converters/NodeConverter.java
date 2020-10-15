package com.reckue.post.utils.converters;

import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.models.Node;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.transfers.NodeRequest;
import com.reckue.post.transfers.NodeResponse;
import com.reckue.post.transfers.nodes.NodeParentResponse;
import com.reckue.post.transfers.nodes.audio.AudioNodeResponse;
import com.reckue.post.transfers.nodes.code.CodeNodeResponse;
import com.reckue.post.transfers.nodes.image.ImageNodeResponse;
import com.reckue.post.transfers.nodes.list.ListNodeResponse;
import com.reckue.post.transfers.nodes.poll.PollNodeResponse;
import com.reckue.post.transfers.nodes.text.TextNodeResponse;
import com.reckue.post.transfers.nodes.video.VideoNodeResponse;

import java.time.ZoneId;
import java.util.Map;

/**
 * Class for converting NodeRequest object to Node and Node object to NodeResponse.
 *
 * @author Viktor Grigoriev
 * @author Dmitrii Lebedev
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

        Map<NodeType, Class<? extends NodeParentResponse>> nodeTypeClassMap = Map.of(
                NodeType.TEXT, TextNodeResponse.class,
                NodeType.IMAGE, ImageNodeResponse.class,
                NodeType.VIDEO, VideoNodeResponse.class,
                NodeType.CODE, CodeNodeResponse.class,
                NodeType.LIST, ListNodeResponse.class,
                NodeType.AUDIO, AudioNodeResponse.class,
                NodeType.POLL, PollNodeResponse.class
        );
        Class<?> nodeClass = nodeTypeClassMap.get(node.getType());

        return NodeResponse.builder()
                .id(node.getId())
                .type(node.getType())
                .parentId(node.getParentId())
                .source(node.getSource())
                .userId(node.getUserId())
                .parentType(node.getParentType())
                .createdDate(node.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .modificationDate(node.getModificationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .node((NodeParentResponse) Converter.convert(node.getNode(), nodeClass))
                .status(node.getStatus())
                .build();
    }
}
