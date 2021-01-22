package com.reckue.post.util.converter;

import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.model.Comment;
import com.reckue.post.model.Node;
import com.reckue.post.transfer.dto.CommentRequest;
import com.reckue.post.transfer.dto.CommentResponse;
import com.reckue.post.transfer.dto.NodeResponse;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for converting CommentRequest object to Comment and Comment object to CommentResponse.
 *
 * @author Artur Magomedov
 */
public class CommentConverter {

    /**
     * Converts from CommentRequest to Comment.
     *
     * @param commentRequest the object of class CommentRequest
     * @return the object of class Comment
     */
    public static Comment convert(CommentRequest commentRequest) {
        if (commentRequest == null) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }

        List<Node> nodes = new ArrayList<>();
        if (commentRequest.getNodes() != null) {
            nodes = commentRequest.getNodes().stream()
                    .map(NodeConverter::convert)
                    .collect(Collectors.toList());
        }

        return Comment.builder()
                .postId(commentRequest.getPostId())
                .commentId(commentRequest.getCommentId())
                .nodes(nodes)
                .build();
    }

    /**
     * Converts from Comment to CommentResponse.
     *
     * @param comment the object of class Comment
     * @return the object of class CommentResponse
     */
    public static CommentResponse convert(Comment comment) {
        if (comment == null) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }
        List<NodeResponse> nodes = new ArrayList<>();
        if (comment.getNodes() != null) {
            nodes = comment.getNodes().stream()
                    .map(NodeConverter::convert)
                    .collect(Collectors.toList());
        }
        return CommentResponse.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .postId(comment.getPostId())
                .nodes(nodes)
                .commentId(comment.getCommentId())
                .createdDate(comment.getCreatedDate()
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .modificationDate(comment.getModificationDate()
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .build();
    }
}
