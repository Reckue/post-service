package com.reckue.post.services.realizations;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Comment;
import com.reckue.post.repositories.CommentRepository;
import com.reckue.post.utils.Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Class CommentServiceRealizationTest represents test for CommentService class.
 *
 * @author Artur Magomedov
 */
public class CommentServiceRealizationTest extends PostServiceApplicationTests {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceRealization commentService;

    @Test
    public void create() {
        String ID = Generator.id();
        Comment comment = Comment.builder().id(ID).text("t").userId("0").postId("2").published(14L).build();
        when(commentRepository.save(comment)).thenReturn(comment);

        assertEquals(comment, commentService.create(comment));
    }

    @Test
    public void createIfCommentAlreadyExist() {
        Comment comment = Comment.builder().id("1").text("a").userId("40").postId("3").published(16L).build();

        doReturn(true).when(commentRepository).existsById(Mockito.anyString());

        Exception exception = assertThrows(ModelAlreadyExistsException.class, () -> commentService.create(comment));
        assertEquals("Comment already exists", exception.getMessage());
    }

    @Test
    public void update() {
        Comment comment = Comment.builder().id("1").text("C++").userId("100").postId("23").published(18L).build();

        when(commentRepository.existsById(comment.getId())).thenReturn(true);
        when(commentRepository.save(comment)).thenReturn(comment);

        Assertions.assertEquals(comment, commentService.update(comment));
    }

    @Test
    public void updateCommentWithNullId() {
        Comment comment = Comment.builder().build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> commentService.update(comment));
        assertEquals("The parameter is null", exception.getMessage());
    }

    @Test
    public void updateCommentIfNotExistId() {
        Comment comment = Comment.builder().id("lop").text("C").userId("9").postId("11").published(20L).build();
        when(commentRepository.existsById(comment.getId())).thenReturn(false);

        Exception exception = assertThrows(ModelNotFoundException.class, () -> commentService.update(comment));
        assertEquals("Comment by id " + comment.getId() + " is not found", exception.getMessage());
    }

    @Test
    public void findById() {
        Comment comment = Comment.builder().id("id").text("go").userId("6").postId("007").published(22L).build();
        doReturn(Optional.of(comment)).when(commentRepository).findById(Mockito.anyString());

        assertEquals(comment, commentService.findById(comment.getId()));
    }

    @Test
    public void findByIdIfNotExist() {
        Comment comment = Comment.builder().id("1").text("rust").userId("16").postId("05").published(24L).build();

        Exception exception = assertThrows(ModelNotFoundException.class, () -> commentService.findById(comment.getId()));
        assertEquals("Comment by id " + comment.getId() + " is not found", exception.getMessage());
    }

    @Test
    public void findAll() {
        Comment comment1 = Comment.builder().id("k").text("turbo").userId("o").postId("f").published(26L).build();
        Comment comment2 = Comment.builder().id("l").text("pascal").userId("p").postId("g").published(28L).build();

        List<Comment> comments = Stream.of(comment1, comment2).collect(Collectors.toList());

        when(commentRepository.findAll()).thenReturn(comments);
        assertEquals(comments, commentService.findAll());
    }

    @Test
    public void findAllAndSortById() {
        Comment comment1 = Comment.builder().id("c").text("df").userId("78").postId("g6").published(13L).build();
        Comment comment2 = Comment.builder().id("35").text("sd").userId("15").postId("e5").published(15L).build();
        Comment comment3 = Comment.builder().id("a").text("ce").userId("69").postId("k8").published(17L).build();

        List<Comment> comments = Stream.of(comment1, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> expected = comments.stream()
                .sorted(Comparator.comparing(Comment::getId))
                .collect(Collectors.toList());

        assertNotEquals(comments, commentService.findAllAndSortById());
        assertEquals(expected, commentService.findAllAndSortById());
    }

    @Test
    public void findAllAndSortByText() {
        Comment comment1 = Comment.builder().id("c").text("df").userId("78").postId("g6").published(13L).build();
        Comment comment2 = Comment.builder().id("35").text("sd").userId("15").postId("e5").published(15L).build();
        Comment comment3 = Comment.builder().id("a").text("ce").userId("69").postId("k8").published(17L).build();

        List<Comment> comments = Stream.of(comment1, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> expected = comments.stream()
                .sorted(Comparator.comparing(Comment::getText))
                .collect(Collectors.toList());

        assertEquals(expected, commentService.findAllAndSortByText());
    }

    @Test
    public void findAllAndSortByUserId() {
        Comment comment1 = Comment.builder().id("c").text("df").userId("78").postId("g6").published(13L).build();
        Comment comment2 = Comment.builder().id("35").text("sd").userId("15").postId("e5").published(15L).build();
        Comment comment3 = Comment.builder().id("a").text("ce").userId("69").postId("k8").published(17L).build();

        List<Comment> comments = Stream.of(comment1, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> expected = comments.stream()
                .sorted(Comparator.comparing(Comment::getUserId))
                .collect(Collectors.toList());

        assertEquals(expected, commentService.findAllAndSortByUserId());
    }

    @Test
    public void findAllAndSortByPostId() {
        Comment comment1 = Comment.builder().id("c").text("df").userId("78").postId("g6").published(13L).build();
        Comment comment2 = Comment.builder().id("35").text("sd").userId("15").postId("e5").published(15L).build();
        Comment comment3 = Comment.builder().id("a").text("ce").userId("69").postId("k8").published(17L).build();

        List<Comment> comments = Stream.of(comment1, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> expected = comments.stream()
                .sorted(Comparator.comparing(Comment::getPostId))
                .collect(Collectors.toList());

        assertEquals(expected, commentService.findAllAndSortByPostId());
    }

    @Test
    public void findAllAndSortByPublished() {
        Comment comment1 = Comment.builder().id("c").text("df").userId("78").postId("g6").published(131L).build();
        Comment comment2 = Comment.builder().id("35").text("sd").userId("15").postId("e5").published(12L).build();
        Comment comment3 = Comment.builder().id("a").text("ce").userId("69").postId("k8").published(6L).build();

        List<Comment> comments = Stream.of(comment1, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> expected = comments.stream()
                .sorted(Comparator.comparing(Comment::getPublished))
                .collect(Collectors.toList());

        assertEquals(expected, commentService.findAllAndSortByPublished());
    }

    @Test
    public void findAllBySortType() {
        Comment comment1 = Comment.builder().id("c").text("df").userId("78").postId("g6").published(23L).build();
        Comment comment2 = Comment.builder().id("35").text("sd").userId("15").postId("e5").published(64L).build();
        Comment comment3 = Comment.builder().id("a").text("ce").userId("695").postId("k8").published(17L).build();

        List<Comment> comments = Stream.of(comment1, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> sortedByIdExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getId))
                .collect(Collectors.toList());

        List<Comment> sortedByTextExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getText))
                .collect(Collectors.toList());

        List<Comment> sortedByUserIdExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getUserId))
                .collect(Collectors.toList());

        List<Comment> sortedByPostIdExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getPostId))
                .collect(Collectors.toList());

        List<Comment> sortedByPublishedExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getPublished))
                .collect(Collectors.toList());

        assertEquals(sortedByIdExpected, commentService.findAllBySortType("id"));
        assertEquals(sortedByTextExpected, commentService.findAllBySortType("text"));
        assertEquals(sortedByUserIdExpected, commentService.findAllBySortType("userId"));
        assertEquals(sortedByPostIdExpected, commentService.findAllBySortType("postId"));
        assertEquals(sortedByPublishedExpected, commentService.findAllBySortType("published"));
    }

    @Test
    public void findAllBySortTypeAndDesc() {
        Comment comment1 = Comment.builder().id("c").text("df").userId("78").postId("g6").published(23L).build();
        Comment comment2 = Comment.builder().id("35").text("sd").userId("15").postId("e5").published(64L).build();
        Comment comment3 = Comment.builder().id("a").text("ce").userId("695").postId("k8").published(17L).build();

        List<Comment> comments = Stream.of(comment1, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> sortedByIdExpectedAndDesc = comments.stream()
                .sorted(Comparator.comparing(Comment::getId).reversed())
                .collect(Collectors.toList());

        List<Comment> sortedByTextAndDescExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getText).reversed())
                .collect(Collectors.toList());

        List<Comment> sortedByUserIdAndDescExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getUserId).reversed())
                .collect(Collectors.toList());

        List<Comment> sortedByPostIdAndDescExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getPostId).reversed())
                .collect(Collectors.toList());

        List<Comment> sortedByPublishedAndDescExpected = comments.stream()
                .sorted(Comparator.comparing(Comment::getPublished).reversed())
                .collect(Collectors.toList());

        assertEquals(sortedByIdExpectedAndDesc, commentService.findAllByTypeAndDesc("id", true));
        assertEquals(sortedByTextAndDescExpected, commentService.findAllByTypeAndDesc("text", true));
        assertEquals(sortedByUserIdAndDescExpected, commentService.findAllByTypeAndDesc("userId", true));
        assertEquals(sortedByPostIdAndDescExpected, commentService.findAllByTypeAndDesc("postId", true));
        assertEquals(sortedByPublishedAndDescExpected, commentService.findAllByTypeAndDesc("published", true));
    }

    @Test
    public void findAllWithLimitOffsetSortAndDesc() {
        Comment comment1 = Comment.builder().id("python").text("7").userId("3").postId("h").published(505L).build();
        Comment comment2 = Comment.builder().id("csharp").text("4").userId("2").postId("u").published(911L).build();
        Comment comment3 = Comment.builder().id("bash").text("6").userId("0").postId("o").published(777L).build();

        List<Comment> comments = Stream.of(comment1, comment2, comment3).collect(Collectors.toList());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> test1 = comments.stream()
                .sorted(Comparator.comparing(Comment::getId))
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        List<Comment> test2 = comments.stream()
                .sorted(Comparator.comparing(Comment::getText).reversed())
                .limit(3)
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
                .sorted(Comparator.comparing(Comment::getPublished).reversed())
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        assertEquals(test1, commentService.findAll(2, 1, "id", false));
        assertEquals(test2, commentService.findAll(3, 0, "text", true));
        assertEquals(test3, commentService.findAll(1, 2, "userId", false));
        assertEquals(test4, commentService.findAll(2, 1, "postId", false));
        assertEquals(test5, commentService.findAll(2, 1, "published", true));
    }

    @Test
    public void deleteById() {
        Comment comment = Comment.builder().id("hr").text("php").userId("1").postId("2").published(111L).build();
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
        Comment comment = Comment.builder().id("not found").text("asm").userId("0").postId("9").published(99L).build();

        Exception exception = assertThrows(ModelNotFoundException.class, () -> commentService.deleteById(comment.getId()));
        assertEquals("Comment by id " + comment.getId() + " is not found", exception.getMessage());
    }
}
