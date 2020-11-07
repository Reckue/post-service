package com.reckue.post.service.impl;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.exception.model.comment.CommentNotFoundException;
import com.reckue.post.model.Comment;
import com.reckue.post.repository.CommentRepository;
import com.reckue.post.repository.NodeRepository;
import com.reckue.post.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Class CommentServiceImplTest represents test for CommentService class.
 *
 * @author Artur Magomedov
 */
@SuppressWarnings("unused")
public class CommentServiceImplTest extends PostServiceApplicationTests {
    private Comment comment;
    private Comment comment2;
    private Comment comment3;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private NodeRepository nodeRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void createComments() {
        comment = Comment.builder()
                .id("javascript")
                .userId("vlad")
                .postId("007")
                .nodes(new ArrayList<>())
                .createdDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(404L), TimeZone.getDefault().toZoneId()))
                .build();

        comment2 = Comment.builder()
                .id("html")
                .userId("ivery")
                .postId("911")
                .nodes(new ArrayList<>())
                .createdDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(500L), TimeZone.getDefault().toZoneId()))
                .build();

        comment3 = Comment.builder()
                .id("java")
                .userId("sherzod")
                .postId("666")
                .nodes(new ArrayList<>())
                .createdDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(200L), TimeZone.getDefault().toZoneId())).
                        build();
    }

    @Disabled
    public void create() {
        when(commentRepository.save(comment)).thenReturn(comment);
        doReturn(true).when(postRepository).existsById(Mockito.anyString());
        doReturn(true).when(commentRepository).existsById(Mockito.isNull());

        assertEquals(comment, commentService.create(comment, new HashMap<>()));
    }

    @Disabled
    public void update() {
        Comment commentRequest = Comment.builder()
                .id("1")
                .userId("2")
                .postId("3")
                .build();

        Comment comment = Comment.builder()
                .id("1")
                .userId("2jjjj")
                .postId("4ijkml")
                .build();
        when(commentRepository.findById(commentRequest.getId())).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);

        commentService.update(commentRequest, new HashMap<>());

        Assertions.assertAll(
                () -> assertEquals(commentRequest.getUserId(), comment.getUserId()),
                () -> assertEquals(commentRequest.getPostId(), comment.getPostId())
        );
    }

    @Test
    public void updateCommentWithNullId() {
        Comment nullableComm = Comment.builder().build();

        Exception exception = assertThrows(ReckueIllegalArgumentException.class,
                () -> commentService.update(nullableComm, new HashMap<>()));
        assertEquals("The parameter is null", exception.getMessage());
    }

    @Test
    public void updateCommentIfNotExistId() {
        when(commentRepository.existsById(comment.getId())).thenReturn(false);

        Exception exception = assertThrows(CommentNotFoundException.class,
                () -> commentService.update(comment, new HashMap<>()));
        assertEquals("Comment by id '" + comment.getId() + "' is not found", exception.getMessage());
    }

    @Test
    public void findById() {
        doReturn(Optional.of(comment)).when(commentRepository).findById(Mockito.anyString());

        assertEquals(comment, commentService.findById(comment.getId()));
    }

    @Test
    public void findByIdIfNotExist() {
        Exception exception = assertThrows(CommentNotFoundException.class,
                () -> commentService.findById(comment.getId()));
        assertEquals("Comment by id '" + comment.getId() + "' is not found", exception.getMessage());
    }

    @Test
    public void findAll() {
        List<Comment> comments = Stream.of(comment, comment2).collect(Collectors.toList());

        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.findById(comment2.getId())).thenReturn(Optional.ofNullable(comment2));

        when(commentRepository.findAll()).thenReturn(comments);
        assertEquals(comments, commentService.findAll());
    }

    @Test
    public void findAllAndSortById() {
        List<Comment> comments = Stream.of(comment, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> expected = comments.stream()
                .sorted(Comparator.comparing(Comment::getId))
                .collect(Collectors.toList());

        for (Comment comment : comments) {
            doReturn(Optional.of(comment)).when(commentRepository).findById(comment.getId());
        }

        assertNotEquals(comments, commentService.findAllAndSortById());
        assertEquals(expected, commentService.findAllAndSortById());
    }

    @Test
    public void findAllAndSortByUserId() {
        List<Comment> comments = Stream.of(comment, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> expected = comments.stream()
                .sorted(Comparator.comparing(Comment::getUserId))
                .collect(Collectors.toList());

        for (Comment comment : comments) {
            doReturn(Optional.of(comment)).when(commentRepository).findById(comment.getId());
        }

        assertEquals(expected, commentService.findAllAndSortByUserId());
    }

    @Test
    public void findAllAndSortByPostId() {
        List<Comment> comments = Stream.of(comment, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> expected = comments.stream()
                .sorted(Comparator.comparing(Comment::getPostId))
                .collect(Collectors.toList());

        for (Comment comment : comments) {
            doReturn(Optional.of(comment)).when(commentRepository).findById(comment.getId());
        }

        assertEquals(expected, commentService.findAllAndSortByPostId());
    }

    @Test
    public void findAllAndSortByPublished() {
        List<Comment> comments = Stream.of(comment, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> expected = comments.stream()
                .sorted(Comparator.comparing(Comment::getCreatedDate))
                .collect(Collectors.toList());

        for (Comment comment : comments) {
            doReturn(Optional.of(comment)).when(commentRepository).findById(comment.getId());
        }

        assertEquals(expected, commentService.findAllAndSortByCreatedDate());
    }

    @Test
    public void findAllBySortType() {
        List<Comment> comments = Stream.of(comment, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> sortedByIdExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getId))
                .collect(Collectors.toList());

        List<Comment> sortedByUserIdExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getUserId))
                .collect(Collectors.toList());

        List<Comment> sortedByPostIdExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getPostId))
                .collect(Collectors.toList());

        List<Comment> sortedByPublishedExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getCreatedDate))
                .collect(Collectors.toList());

        for (Comment comment : comments) {
            doReturn(Optional.of(comment)).when(commentRepository).findById(comment.getId());
        }

        assertEquals(sortedByIdExpected, commentService.findAllBySortType("id"));
        assertEquals(sortedByUserIdExpected, commentService.findAllBySortType("userId"));
        assertEquals(sortedByPostIdExpected, commentService.findAllBySortType("postId"));
        assertEquals(sortedByPublishedExpected, commentService.findAllBySortType("createdDate"));
    }

    @Test
    public void findAllBySortTypeAndDesc() {
        List<Comment> comments = Stream.of(comment, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> sortByIdAndDescExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getId).reversed())
                .collect(Collectors.toList());

        List<Comment> sortByUserIdAndDescExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getUserId).reversed())
                .collect(Collectors.toList());

        List<Comment> sortByPostIdAndDescExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getPostId).reversed())
                .collect(Collectors.toList());

        List<Comment> sortByPublishedAndDescExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getCreatedDate).reversed())
                .collect(Collectors.toList());

        for (Comment comment : comments) {
            doReturn(Optional.of(comment)).when(commentRepository).findById(comment.getId());
        }

        assertEquals(sortByIdAndDescExpected, commentService.findAllByTypeAndDesc("id", true));
        assertEquals(sortByUserIdAndDescExpected, commentService.findAllByTypeAndDesc("userId", true));
        assertEquals(sortByPostIdAndDescExpected, commentService.findAllByTypeAndDesc("postId", true));
        assertEquals(sortByPublishedAndDescExpected, commentService.findAllByTypeAndDesc("createdDate", true));
    }

    @Test
    public void findAllWithLimitOffsetSortAndDesc() {
        List<Comment> comments = Stream.of(comment, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> test1 = comments.stream()
                .sorted(Comparator.comparing(Comment::getId))
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        List<Comment> test3 = comments.stream()
                .sorted(Comparator.comparing(Comment::getUserId))
                .limit(1)
                .skip(2)
                .collect(Collectors.toList());

        List<Comment> test4 = comments.stream()
                .sorted(Comparator.comparing(Comment::getPostId))
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        List<Comment> test5 = comments.stream()
                .sorted(Comparator.comparing(Comment::getCreatedDate).reversed())
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        for (Comment comment : comments) {
            doReturn(Optional.of(comment)).when(commentRepository).findById(comment.getId());
        }

        assertEquals(test1, commentService.findAll(2, 1, "id", false));
        assertEquals(test3, commentService.findAll(1, 2, "userId", false));
        assertEquals(test4, commentService.findAll(2, 1, "postId", false));
        assertEquals(test5, commentService.findAll(2, 1, "createdDate", true));
    }

    @Test
    public void deleteById() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        when(commentRepository.existsById(comment.getId())).thenReturn(true);
        doAnswer(invocation -> {
            comments.remove(comment);
            return null;
        }).when(commentRepository).deleteById(comment.getId());
        commentRepository.deleteById(comment.getId());

        assertEquals(0, comments.size());
    }

    @Test
    public void deleteByIdWithException() {
        Exception exception = assertThrows(CommentNotFoundException.class,
                () -> commentService.deleteById(comment.getId(), new HashMap<>()));
        assertEquals("Comment by id '" + comment.getId() + "' is not found", exception.getMessage());
    }
}
