package com.reckue.post.util.converter;

import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.generated.controller.dto.CommentRequestDto;
import com.reckue.post.generated.controller.dto.CommentResponseDto;
import com.reckue.post.model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class for converting CommentRequestDto object to Comment and Comment object to CommentResponse.
 *
 * @author Artur Magomedov
 */
public class CommentConverter {

    /**
     * Converts from CommentRequestDto to Comment.
     *
     * @param commentRequest the object of class CommentRequestDto
     * @return the object of class Comment
     */
    public static Comment convertToModel(CommentRequestDto commentRequest) {
        if (Objects.isNull(commentRequest)) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }

        return Comment.builder()
                .postId(commentRequest.getPostId())
                .commentId(commentRequest.getCommentId())
                .nodes(Optional.ofNullable(commentRequest.getNodes())
                        .orElse(new ArrayList<>()).stream()
                        .map(NodeConverter::convertToModel)
                        .collect(Collectors.toList()))
                .build();
    }

    /**
     * Converts from Comment to CommentResponse.
     *
     * @param comment the object of class Comment
     * @return the object of class CommentResponse
     */
    public static CommentResponseDto convertToDto(Comment comment) {
        if (Objects.isNull(comment)) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }

        return CommentResponseDto.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .postId(comment.getPostId())
                .nodes(Optional.ofNullable(comment.getNodes())
                        .orElse(List.of()).stream()
                        .map(NodeConverter::convertToDto)
                        .collect(Collectors.toList()))
                .commentId(comment.getCommentId())
//                .createdDate(comment.getCreatedDate()
//                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
//                .modificationDate(comment.getModificationDate()
//                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .build();
    }

    public static List<CommentResponseDto> convertToDtoList(List<Comment> comments) {
        return Optional.ofNullable(comments)
                .orElse(List.of()).stream()
                .map(CommentConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
