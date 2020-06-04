package com.reckue.post.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reckue.post.models.Post;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.transfers.PostResponse;
import com.reckue.post.utils.converters.PostConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class PostControllerIntTest is the integration type of test
 * todo all tests for all methods
 * todo test for findAll method with all parameters and cases
 *
 * @author Kamila Meshcheryakova
 */
@SpringBootTest
@ActiveProfiles("staging")
@AutoConfigureMockMvc
public class PostControllerIntTest {

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
        // 1
        postRepository.save(Post.builder()
                .id("1")
                .title("string")
                .build());
        postRepository.save(Post.builder()
                .id("3")
                .title("pupil")
                .build());
        postRepository.save(Post.builder()
                .id("2")
                .title("title")
                .build());
    }

    @Test
    public void load() {
        assertThat(postController).isNotNull();
    }

    @Test
    public void findAllSortedByIdDesc() throws Exception {
        List<PostResponse> expected = postRepository.findAll().stream()
                .map(PostConverter::convert)
                .sorted(Comparator.comparing(PostResponse::getId))
                .collect(Collectors.toList());

        Collections.reverse(expected);

        expected = expected.stream()
                .limit(2)
                .collect(Collectors.toList());

        List<PostResponse> actual = objectMapper.readValue(this.mockMvc.perform(get("/posts?desc=true&limit=2&offset=0&sort=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByTitleAsc() throws Exception {
        List<PostResponse> expected = postRepository.findAll().stream()
                .map(PostConverter::convert)
                .sorted(Comparator.comparing(PostResponse::getTitle))
                .limit(2)
                .collect(Collectors.toList());

        List<PostResponse> actual = objectMapper.readValue(this.mockMvc.perform(get("/posts?desc=false&limit=2&offset=0&sort=title"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/posts?desc=true&limit=1&offset=0&sort=id"))
                .andDo(print()).andExpect(status()
                .isOk())
                .andExpect(content().string(containsString(
                        "[]")));
    }

    @Test
    public void getById() throws Exception {
        PostResponse expected = PostConverter.convert(postRepository.findAll().get(0));

        PostResponse actual = objectMapper.readValue(this.mockMvc.perform(get("/posts/" + expected.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), PostResponse.class);

        Assertions.assertEquals(expected, actual);
    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void save() {
//    }
//
//    @Test
//    void deleteById() {
//    }
}