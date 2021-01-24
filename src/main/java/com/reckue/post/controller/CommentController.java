package com.reckue.post.controller;

import com.reckue.post.generated.controller.CommentsApi;
import com.reckue.post.generated.controller.dto.CommentRequestDto;
import com.reckue.post.generated.controller.dto.CommentResponseDto;
import com.reckue.post.model.Comment;
import com.reckue.post.service.CommentService;
import com.reckue.post.util.converter.CommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Class CommentController is responsible for processing incoming requests.
 *
 * @author Artur Magomedov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController implements CommentsApi {

    private final CommentService commentService;

    @Override
    public ResponseEntity<CommentResponseDto> createComment(@Valid CommentRequestDto commentRequestDto) {
        Comment comment = CommentConverter.convertToModel(commentRequestDto);
        Comment storedComment = commentService.create(comment);
        return new ResponseEntity<>(CommentConverter.convertToDto(storedComment), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommentResponseDto> updateComment(String id, @Valid CommentRequestDto commentRequestDto) {
        Comment comment = CommentConverter.convertToModel(commentRequestDto);
        comment.setId(id);
        return ResponseEntity.ok(CommentConverter.convertToDto(commentService.update(comment)));
    }

    @Override
    public ResponseEntity<List<CommentResponseDto>> getAllComments(@Valid Integer limit, @Valid Integer offset,
                                                                   @Valid String sort, @Valid Boolean desc) {
        List<Comment> comments = commentService.findAll(limit, offset, sort, desc);
        List<CommentResponseDto> convertedComments = CommentConverter.convertToDtoList(comments);
        return new ResponseEntity<>(convertedComments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommentResponseDto> getCommentById(String id) {
        CommentResponseDto convertedComment = CommentConverter.convertToDto(commentService.findById(id));
        return new ResponseEntity<>(convertedComment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteCommentById(String id) {
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
