package com.reckue.post.utils.converters;

import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.models.CommentNode;
import com.reckue.post.models.commentNodes.*;
import com.reckue.post.models.types.CommentNodeType;
import com.reckue.post.transfers.CommentNodeRequest;
import com.reckue.post.transfers.CommentNodeResponse;
import com.reckue.post.transfers.commentNodes.CommentNodeParentResponse;
import com.reckue.post.transfers.commentNodes.audio.CommentAudioNodeResponse;
import com.reckue.post.transfers.commentNodes.code.CommentCodeNodeResponse;
import com.reckue.post.transfers.commentNodes.image.CommentImageNodeResponse;
import com.reckue.post.transfers.commentNodes.list.CommentListNodeResponse;
import com.reckue.post.transfers.commentNodes.text.CommentTextNodeResponse;
import com.reckue.post.transfers.commentNodes.video.CommentVideoNodeResponse;

import java.time.ZoneId;
import java.util.Map;

/**
 * Class for converting CommentNodeRequest object to CommentNode and CommentNode object to CommentNodeResponse.
 *
 * @author Artur Magomedov
 */
public class CommentNodeConverter {

    /**
     * Converts from CommentNodeRequest to CommentNode.
     *
     * @param nodeRequest the object of class CommentNodeRequest
     * @return the object of class CommentNode
     */
    public static CommentNode convert(CommentNodeRequest nodeRequest) {
        if (nodeRequest == null) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }
        Map<CommentNodeType, Class<?>> map = Map.of(
                CommentNodeType.TEXT, CommentTextNode.class,
                CommentNodeType.IMAGE, CommentImageNode.class,
                CommentNodeType.VIDEO, CommentVideoNode.class,
                CommentNodeType.CODE, CommentCodeNode.class,
                CommentNodeType.LIST, CommentListNode.class,
                CommentNodeType.AUDIO, CommentAudioNode.class
        );

        Class<?> targetClass = map.get(nodeRequest.getType());

        return CommentNode.builder()
                .type(nodeRequest.getType())
                .source(nodeRequest.getSource())
                .commentId(nodeRequest.getCommentId())
                .node((CommentParent) Converter.convert(nodeRequest.getNode(), targetClass))
                .build();
    }

    /**
     * Converts from CommentNode to CommentNodeResponse.
     *
     * @param node the object of class CommentNode
     * @return the object of class CommentNodeResponse
     */
    public static CommentNodeResponse convert(CommentNode node) {
        if (node == null) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }

        Map<CommentNodeType, Class<?>> map = Map.of(
                CommentNodeType.TEXT, CommentTextNodeResponse.class,
                CommentNodeType.IMAGE, CommentImageNodeResponse.class,
                CommentNodeType.VIDEO, CommentVideoNodeResponse.class,
                CommentNodeType.CODE, CommentCodeNodeResponse.class,
                CommentNodeType.LIST, CommentListNodeResponse.class,
                CommentNodeType.AUDIO, CommentAudioNodeResponse.class
        );
        Class<?> targetClass = map.get(node.getType());

        return CommentNodeResponse.builder()
                .id(node.getId())
                .type(node.getType())
                .source(node.getSource())
                .commentId(node.getCommentId())
                .createdDate(node.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .modificationDate(node.getModificationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .node((CommentNodeParentResponse) Converter.convert(node.getNode(), targetClass))
                .build();
    }
}
