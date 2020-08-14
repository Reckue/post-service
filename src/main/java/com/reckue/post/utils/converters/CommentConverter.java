package com.reckue.post.utils.converters;

import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.models.Comment;
import com.reckue.post.transfers.CommentRequest;
import com.reckue.post.transfers.CommentResponse;

import java.time.ZoneId;

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
        return Comment.builder()
                .text(commentRequest.getText())
                .userId(commentRequest.getUserId())
                .postId(commentRequest.getPostId())
                .commentId(commentRequest.getCommentId())
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
        return CommentResponse.builder()
                .id(comment.getId())
                .text(comment.getText())
                .userId(comment.getUserId())
                .postId(comment.getPostId())
                .commentId(comment.getCommentId())
                .createdDate(comment.getCreatedDate()
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .modificationDate(comment.getModificationDate()
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .build();
    }
}
