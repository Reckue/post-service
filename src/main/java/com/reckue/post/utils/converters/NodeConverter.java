package com.reckue.post.utils.converters;

import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.models.Comment;
import com.reckue.post.models.Node;
import com.reckue.post.models.Post;
import com.reckue.post.models.nodes.*;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.models.types.ParentType;
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
        Map<NodeType, Class<?>> nodeTypeClassMap = Map.of(
                NodeType.TEXT, TextNode.class,
                NodeType.IMAGE, ImageNode.class,
                NodeType.VIDEO, VideoNode.class,
                NodeType.CODE, CodeNode.class,
                NodeType.LIST, ListNode.class,
                NodeType.AUDIO, AudioNode.class,
                NodeType.POLL, PollNode.class
        );
        Class<?> nodeClass = nodeTypeClassMap.get(nodeRequest.getType());

        Map<ParentType, Class<?>> parentTypeClassMap = Map.of(
                ParentType.POST, Post.class,
                ParentType.COMMENT, Comment.class
        );
        Class<?> parentClass = parentTypeClassMap.get(nodeRequest.getParentType());

        return Node.builder()
                .type(nodeRequest.getType())
                .parentId(nodeRequest.getParentId())
                .userId(nodeRequest.getUserId())
                .source(nodeRequest.getSource())
                .node((Parent) Converter.convert(nodeRequest.getNode(), nodeClass))
                .parentType((ParentType) Converter.convert(nodeRequest.getParentType(), parentClass))
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

        Map<NodeType, Class<?>> nodeTypeClassMap = Map.of(
                NodeType.TEXT, TextNodeResponse.class,
                NodeType.IMAGE, ImageNodeResponse.class,
                NodeType.VIDEO, VideoNodeResponse.class,
                NodeType.CODE, CodeNodeResponse.class,
                NodeType.LIST, ListNodeResponse.class,
                NodeType.AUDIO, AudioNodeResponse.class,
                NodeType.POLL, PollNodeResponse.class
        );
        Class<?> nodeClass = nodeTypeClassMap.get(node.getType());

        Map<ParentType, Class<?>> parentTypeClassMap = Map.of(
                ParentType.POST, Post.class,
                ParentType.COMMENT, Comment.class
        );
        Class<?> parentClass = parentTypeClassMap.get(node.getParentType());

        return NodeResponse.builder()
                .id(node.getId())
                .type(node.getType())
                .parentId(node.getParentId())
                .source(node.getSource())
                .userId(node.getUserId())
                .createdDate(node.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .modificationDate(node.getModificationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .node((NodeParentResponse) Converter.convert(node.getNode(), nodeClass))
                .parentType((ParentType) Converter.convert(node.getParentType(), parentClass))
                .status(node.getStatus())
                .build();
    }
}
