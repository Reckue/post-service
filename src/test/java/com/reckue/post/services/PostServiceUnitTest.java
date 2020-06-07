package com.reckue.post.services;

import com.reckue.post.models.Post;
import com.reckue.post.models.StatusType;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.services.realizations.PostServiceRealization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("staging")
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PostServiceUnitTest {

    private final Post emptyPost = Post.builder().build();

    private final Post validPost = Post.builder()
            .id("1").title("Java: 'Hello world' application...").source("Github.com")
            .published(1491379425L).changed(1491465825L)
            .status(StatusType.DELETED).username("Publisher321")
            .build();

    private final Post setUpPost = Post.builder()
            .id("0").title("Setup post").source("Github.com")
            .published(1491379425L).changed(1491465825L)
            .status(StatusType.DELETED).username("Publisher321")
            .build();

    @Resource
    private PostServiceRealization postServiceRealization;

    @BeforeEach
    public void setUp() {
        Post created = postServiceRealization.create(setUpPost);
        setUpPost.setId(created.getId());
    }

    @Test
    public void createEmptyPost() {
        Post expected = emptyPost;
        Post actual = postServiceRealization.create(emptyPost);
        assertNotEquals(null, actual.getId());
        actual.setId(null);
        assertEquals(expected, actual);
    }

    @Test
    public void createValidPost() {
        Post expected = validPost;
        Post actual = postServiceRealization.create(validPost);
        assertNotEquals(null, actual.getId());
        actual.setId(null);
        assertEquals(expected, actual);
    }

    @Test
    public void getPostById() {
        Post expected = setUpPost;
        Post actual = postServiceRealization.findById(setUpPost.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void deletePostById() {

    }
}
