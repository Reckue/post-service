package com.reckue.post.services.realizations;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Post;
import com.reckue.post.repositories.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

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

        Assertions.assertEquals(post, postService.create(post));
    }

    @Test
    public void createIfExists() {
        Post post = Post.builder()
                .id("1")
                .title("post")
                .build();
        when(postRepository.save(post)).thenReturn(post);

        Assertions.assertEquals(post, postService.create(post));
    }

    @Test
    public void update() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.existsById(postOne.getId())).thenReturn(true);
        when(postRepository.save(postOne)).thenReturn(postOne);
        Assertions.assertEquals(postOne, postService.update(postOne));
    }

    @Test
    public void updateWithExistId() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.existsById(postOne.getId())).thenReturn(false);
        when(postRepository.save(postOne)).thenReturn(postOne);
        Assertions.assertThrows(ModelNotFoundException.class, () ->  postService.update(postOne));
    }

    @Test
    public void findById() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.findById(postOne.getId())).thenReturn(Optional.of(postOne));
        Assertions.assertEquals(postOne, postService.findById(postOne.getId()));
    }

    @Test
    public void findByIdWithException() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.findById(postOne.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(ModelNotFoundException.class, () ->  postService.findById(postOne.getId()));
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

        Assertions.assertEquals(posts, postService.findAll());
    }

    @Test
    public void deleteById() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.existsById(postOne.getId())).thenReturn(false);
        Assertions.assertThrows(ModelNotFoundException.class, () -> postService.deleteById(postOne.getId()));
    }

    @Test
    public void deleteByIdWithException() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.existsById(postOne.getId())).thenReturn(false);
        Assertions.assertThrows(ModelNotFoundException.class, () -> postService.deleteById(postOne.getId()));
    }
}