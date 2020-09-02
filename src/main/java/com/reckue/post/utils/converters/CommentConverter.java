package com.reckue.post.utils.converters;

import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.models.Comment;
import com.reckue.post.models.CommentNode;
import com.reckue.post.models.Node;
import com.reckue.post.transfers.CommentNodeResponse;
import com.reckue.post.transfers.CommentRequest;
import com.reckue.post.transfers.CommentResponse;

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

        List<CommentNode> nodes = new ArrayList<>();
        if (commentRequest.getNodes() != null) {
            nodes = commentRequest.getNodes().stream()
                    .map(CommentNodeConverter::convert)
                    .collect(Collectors.toList());
        }

        return Comment.builder()
                .userId(commentRequest.getUserId())
                .postId(commentRequest.getPostId())
                .commentId(commentRequest.getCommentId())
                .commentNodes(nodes)
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
        List<CommentNodeResponse> nodes = new ArrayList<>();
        if (comment.getCommentNodes() != null) {
            nodes = comment.getCommentNodes().stream()
                    .map(CommentNodeConverter::convert)
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
