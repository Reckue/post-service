package com.reckue.post.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.models.Post;
import com.reckue.post.models.StatusType;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.transfers.PostRequest;
import com.reckue.post.transfers.PostResponse;
import com.reckue.post.utils.converters.PostConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class PostControllerIntTest is the integration type of test
 *
 * @author Kamila Meshcheryakova
 */
@ActiveProfiles("staging")
@AutoConfigureMockMvc
public class PostControllerIntTest extends PostServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostController postController;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        postRepository.deleteAll();

        postRepository.save(Post.builder()
                .id("4")
                .title("oracle")
                .source("Github.com")
                .published(1491379425L)
                .changed(1491465825L)
                .status(StatusType.DELETED)
                .username("daria")
                .build());
        postRepository.save(Post.builder()
                .id("1")
                .title("string")
                .source("Wikipedia.com")
                .published(1591379425L)
                .changed(1591465825L)
                .status(StatusType.ACTIVE)
                .username("egnaf")
                .build());
        postRepository.save(Post.builder()
                .id("3")
                .title("pupil")
                .source("Google.com")
                .published(1601920225L)
                .changed(1602006625L)
                .status(StatusType.BANNED)
                .username("camelya")
                .build());
        postRepository.save(Post.builder()
                .id("2")
                .title("title")
                .source("Habr.com")
                .published(1701920225L)
                .changed(1702006625L)
                .status(StatusType.MODERATED)
                .username("hardele")
                .build());
    }

    @Test
    public void loadTest() {
        assertThat(postController).isNotNull();
    }

    @Test
    public void findByIdTest() throws Exception {
        PostResponse expected = PostConverter.convert(postRepository.findAll().get(0));

        PostResponse actual = objectMapper.readValue(this.mockMvc
                .perform(get("/posts/" + expected.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), PostResponse.class);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByIdDescTest() throws Exception {
        List<PostResponse> expected = postRepository.findAll().stream()
                .map(PostConverter::convert)
                .sorted(Comparator.comparing(PostResponse::getId))
                .collect(Collectors.toList());

        Collections.reverse(expected);

        expected = expected.stream()
                .limit(2)
                .collect(Collectors.toList());

        List<PostResponse> actual = objectMapper.readValue(this.mockMvc
                .perform(get("/posts?desc=true&limit=2&offset=0&sort=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByTitleAscTest() throws Exception {
        List<PostResponse> expected = postRepository.findAll().stream()
                .map(PostConverter::convert)
                .sorted(Comparator.comparing(PostResponse::getTitle))
                .limit(3)
                .collect(Collectors.toList());

        List<PostResponse> actual = objectMapper.readValue(this.mockMvc
                .perform(get("/posts?desc=false&limit=3&offset=0&sort=title"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedBySourceDescTest() throws Exception {
        List<PostResponse> expected = postRepository.findAll().stream()
                .map(PostConverter::convert)
                .sorted(Comparator.comparing(PostResponse::getSource))
                .collect(Collectors.toList());

        Collections.reverse(expected);

        expected = expected.stream()
                .limit(3)
                .collect(Collectors.toList());

        List<PostResponse> actual = objectMapper.readValue(this.mockMvc
                .perform(get("/posts?desc=true&limit=3&offset=0&sort=source"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByPublishedAscTest() throws Exception {
        List<PostResponse> expected = postRepository.findAll().stream()
                .map(PostConverter::convert)
                .sorted(Comparator.comparing(PostResponse::getPublished))
                .limit(2)
                .collect(Collectors.toList());

        List<PostResponse> actual = objectMapper.readValue(this.mockMvc
                .perform(get("/posts?desc=false&limit=2&offset=0&sort=published"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByChangedDescTest() throws Exception {
        List<PostResponse> expected = postRepository.findAll().stream()
                .map(PostConverter::convert)
                .sorted(Comparator.comparing(PostResponse::getChanged))
                .collect(Collectors.toList());

        Collections.reverse(expected);

        expected = expected.stream()
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        List<PostResponse> actual = objectMapper.readValue(this.mockMvc
                .perform(get("/posts?desc=true&limit=2&offset=1&sort=changed"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByStatusAscTest() throws Exception {
        List<PostResponse> expected = postRepository.findAll().stream()
                .map(PostConverter::convert)
                .sorted(Comparator.comparing(PostResponse::getStatus))
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        List<PostResponse> actual = objectMapper.readValue(this.mockMvc
                .perform(get("/posts?desc=false&limit=2&offset=1&sort=status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void saveTest() throws Exception {
        PostRequest postRequest = PostRequest.builder()
                .title("news")
                .nodes(null)
                .source("Habr.com")
                .tags(null)
                .username("camelya")
                .published(1591465825L)
                .changed(1591465825L)
                .status(StatusType.MODERATED)
                .build();

        PostResponse actual = objectMapper.readValue(this.mockMvc
                .perform(MockMvcRequestBuilders.post("/posts")
                        .content(objectMapper.writeValueAsString(postRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), PostResponse.class);

        PostResponse expected = PostConverter.convert(PostConverter.convert(postRequest));
        expected.setId(actual.getId());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void updateTest() throws Exception {
        PostRequest postRequest = PostRequest.builder()
                .title("title")
                .nodes(null)
                .source("Habr.com")
                .tags(null)
                .username("hardele")
                .published(1701920225L)
                .changed(1802006625L)
                .status(StatusType.ACTIVE)
                .build();

        PostResponse actual = objectMapper.readValue(this.mockMvc
                .perform(MockMvcRequestBuilders.put("/posts/2")
                        .content(objectMapper.writeValueAsString(postRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), PostResponse.class);

        PostResponse expected = PostConverter.convert(PostConverter.convert(postRequest));
        expected.setId(actual.getId());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void deleteByIdTest() throws Exception {
        int size = postRepository.findAll().size();
        this.mockMvc.perform(delete("/posts/" + postRepository.findAll().get(0).getId()))
                .andDo(print())
                .andExpect(status().isOk());
        Assertions.assertEquals(size - 1, postRepository.findAll().size());
    }
}
