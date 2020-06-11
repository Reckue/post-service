package com.reckue.post.services.realizations;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Post;
import com.reckue.post.models.StatusType;
import com.reckue.post.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PostService class.
 *
 * @author Viktor Grigoriev
 */
class PostServiceRealizationTest extends PostServiceApplicationTests {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceRealization postService;

    @Test
    public void create() {
        Post post = Post.builder()
                .id("1")
                .title("post")
                .build();
        when(postRepository.save(post)).thenReturn(post);

        assertEquals(post, postService.create(post));
    }

    @Test
    public void createWithExistId() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        doReturn(true).when(postRepository).existsById(Mockito.anyString());

        assertThrows(ModelAlreadyExistsException.class, () -> postService.create(postOne));
    }

    @Test
    public void update() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.existsById(postOne.getId())).thenReturn(true);
        when(postRepository.save(postOne)).thenReturn(postOne);

        assertEquals(postOne, postService.update(postOne));
    }

    @Test
    public void updateWithNullId() {
        Post postOne = Post.builder()
                .title("postOne")
                .build();

        assertThrows(IllegalArgumentException.class, () -> postService.update(postOne));
    }

    @Test
    public void updateWithExistId() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.existsById(postOne.getId())).thenReturn(false);
        when(postRepository.save(postOne)).thenReturn(postOne);

        assertThrows(ModelNotFoundException.class, () -> postService.update(postOne));
    }

    @Test
    public void findById() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.findById(postOne.getId())).thenReturn(Optional.of(postOne));

        assertEquals(postOne, postService.findById(postOne.getId()));
    }

    @Test
    public void findByIdWithException() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.findById(postOne.getId())).thenReturn(Optional.empty());

        assertThrows(ModelNotFoundException.class, () -> postService.findById(postOne.getId()));
    }

    @Test
    public void findAll() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        Post postTwo = Post.builder()
                .id("2")
                .title("postTwo")
                .build();
        List<Post> posts = List.of(postOne, postTwo);
        when(postRepository.findAll()).thenReturn(posts);

        assertEquals(posts, postService.findAll());
    }

    @Test
    public void findAllSortById() {
        Post postOne = Post.builder().id("1").build();
        Post postTwo = Post.builder().id("2").build();
        Post postThree = Post.builder().id("3").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getId))
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllAndSortById());
    }

    @Test
    public void findAllSortByTitle() {
        Post postOne = Post.builder().title("postOne").build();
        Post postTwo = Post.builder().title("postTwo").build();
        Post postThree = Post.builder().title("postThree").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getTitle))
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllAndSortByTitle());
    }

    @Test
    public void findAllSortBySource() {
        Post postOne = Post.builder().source("sourceOne").build();
        Post postTwo = Post.builder().source("sourceTwo").build();
        Post postThree = Post.builder().source("sourceThree").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getSource))
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllAndSortBySource());
    }

    @Test
    public void findAllSortByUsername() {
        Post postOne = Post.builder().username("Max").build();
        Post postTwo = Post.builder().username("Will").build();
        Post postThree = Post.builder().username("Arny").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getUsername))
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllAndSortByUsername());
    }

    @Test
    public void findAllSortByPublished() {
        Post postOne = Post.builder().published(1).build();
        Post postTwo = Post.builder().published(2).build();
        Post postThree = Post.builder().published(3).build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getPublished))
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllAndSortByPublished());
    }

    @Test
    public void findAllSortByChanged() {
        Post postOne = Post.builder().changed(1).build();
        Post postTwo = Post.builder().changed(2).build();
        Post postThree = Post.builder().changed(3).build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getChanged))
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllAndSortByChanged());
    }

    @Test
    public void findAllSortByStatus() {
        Post postOne = Post.builder().status(StatusType.ACTIVE).build();
        Post postTwo = Post.builder().status(StatusType.BANNED).build();
        Post postThree = Post.builder().status(StatusType.DELETED).build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getStatus))
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllAndSortByStatus());
    }

    @Test
    public void findAllSortByTypeAndDesc() {
        Post postOne = Post.builder().username("Max").build();
        Post postTwo = Post.builder().username("Will").build();
        Post postThree = Post.builder().username("Arny").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);
        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getUsername).reversed())
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllByTypeAndDesc("username", true));
    }

    @Test
    public void findAllWithArgs() {
        Post postOne = Post.builder().username("Max").build();
        Post postTwo = Post.builder().username("Will").build();
        Post postThree = Post.builder().username("Arny").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getUsername).reversed())
                .limit(3)
                .skip(1)
                .collect(Collectors.toList());
        System.out.println(expected);

        assertEquals(expected, postService.findAll(3, 1, "username", true));
    }

    @Test
    public void findAllWithIllegalArgLimit() {
        assertThrows(IllegalArgumentException.class,
                () -> postService.findAll(-1, 1, "name", true));
    }

    @Test
    public void findAllWithIllegalArgOffset() {
        assertThrows(IllegalArgumentException.class,
                () -> postService.findAll(1, -1, "name", true));
    }

    @Test
    public void deleteById() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        List<Post> posts = new ArrayList<>();
        posts.add(postOne);
        when(postRepository.existsById(postOne.getId())).thenReturn(true);
        doAnswer(invocation -> {
            posts.remove(postOne);
            return null;
        }).when(postRepository).deleteById(postOne.getId());
        postService.deleteById(postOne.getId());

        assertEquals(0, posts.size());
    }

    @Test
    public void deleteByIdWithNotFoundException() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.existsById(postOne.getId())).thenReturn(false);

        assertThrows(ModelNotFoundException.class, () -> postService.deleteById(postOne.getId()));
    }
}
