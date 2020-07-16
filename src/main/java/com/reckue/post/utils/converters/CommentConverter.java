package com.reckue.post.utils.converters;

import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.models.Comment;
import com.reckue.post.transfers.CommentRequest;
import com.reckue.post.transfers.CommentResponse;

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
                .comments(commentRequest.getComments())
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
                .createdDate(comment.getCreatedDate())
                .comments(comment.getComments())
                .build();
    }
}
