package com.reckue.post.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.models.Comment;
import com.reckue.post.repositories.CommentRepository;
import com.reckue.post.transfers.CommentRequest;
import com.reckue.post.transfers.CommentResponse;
import com.reckue.post.utils.converters.CommentConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Class RatingControllerIntTest is the integration type of test.
 *
 * @author Iveri Narozashvili
 */
@AutoConfigureMockMvc
public class RatingControllerIntTest extends PostServiceApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentController commentController;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        commentRepository.deleteAll();

        commentRepository.save(Comment.builder()
                .id("1")
                .text("comment1")
                .userId("anton")
                .postId("planets")
                .published(1491379425L)
                .build());
        commentRepository.save(Comment.builder()
                .id("2")
                .text("comment5")
                .userId("vlad cepesh")
                .postId("books")
                .published(1591379425L)
                .build());
        commentRepository.save(Comment.builder()
                .id("3")
                .text("comment10")
                .userId("semen")
                .postId("cows")
                .published(1601920225L)
                .build());
    }

    @Test
    public void load() {
        assertThat(commentController).isNotNull();
    }

    @Test
    public void findAllSortedByIdAsc() throws Exception {
        List<CommentResponse> expected = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .limit(3)
                .peek(System.out::println)
                .collect(Collectors.toList());

        List<CommentResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/comments?desc=false&limit=3&offset=0&sort=id"))
                        .andDo(print()).andExpect(status()
                                .isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByIdDesc() throws Exception {
        List<CommentResponse> expected = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .sorted(Comparator.comparing(CommentResponse::getId).reversed())
                .limit(2)
                .collect(Collectors.toList());

        List<CommentResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/comments?desc=true&limit=2&offset=0&sort=id"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByTextAsc() throws Exception {
        List<CommentResponse> expected = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .sorted(Comparator.comparing(CommentResponse::getText))
                .limit(2)
                .collect(Collectors.toList());

        List<CommentResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/comments?desc=false&limit=2&offset=0&sort=text"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByTextDesc() throws Exception {
        List<CommentResponse> expected = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .sorted(Comparator.comparing(CommentResponse::getText).reversed())
                .limit(2)
                .collect(Collectors.toList());

        List<CommentResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/comments?desc=true&limit=2&offset=0&sort=text"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByUserIdAsc() throws Exception {
        List<CommentResponse> expected = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .sorted(Comparator.comparing(CommentResponse::getUserId))
                .limit(2)
                .collect(Collectors.toList());

        List<CommentResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/comments?desc=false&limit=2&offset=0&sort=userId"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByUserIdDesc() throws Exception {
        List<CommentResponse> expected = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .sorted(Comparator.comparing(CommentResponse::getUserId).reversed())
                .limit(2)
                .collect(Collectors.toList());

        List<CommentResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/comments?desc=true&limit=2&offset=0&sort=userId"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByPostIdAsc() throws Exception {
        List<CommentResponse> expected = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .sorted(Comparator.comparing(CommentResponse::getPostId))
                .limit(2)
                .collect(Collectors.toList());

        List<CommentResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/comments?desc=false&limit=2&offset=0&sort=postId"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByPostIdDesc() throws Exception {
        List<CommentResponse> expected = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .sorted(Comparator.comparing(CommentResponse::getPostId).reversed())
                .limit(2)
                .collect(Collectors.toList());

        List<CommentResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/comments?desc=true&limit=2&offset=0&sort=postId"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByPublishedAsc() throws Exception {
        List<CommentResponse> expected = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .sorted(Comparator.comparing(CommentResponse::getPublished))
                .limit(2)
                .collect(Collectors.toList());

        List<CommentResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/comments?desc=false&limit=2&offset=0&sort=published"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByPublishedDesc() throws Exception {
        List<CommentResponse> expected = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .sorted(Comparator.comparing(CommentResponse::getPublished).reversed())
                .limit(2)
                .collect(Collectors.toList());

        List<CommentResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/comments?desc=true&limit=2&offset=0&sort=published"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findById() throws Exception {
        CommentResponse expected = CommentConverter.convert(commentRepository.findAll().get(0));

        CommentResponse actual = objectMapper.readValue(this.mockMvc.perform(get("/comments/" + expected.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), CommentResponse.class);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update() throws Exception {
        CommentResponse expected = CommentConverter.convert(Comment.builder()
                .id(commentRepository.findAll().get(0).getId())
                .text("eleven")
                .userId("best id ever")
                .postId("simple")
                .published(1491379422L)
                .build());

        String json = objectMapper.writeValueAsString(CommentRequest.builder()
                .text("eleven")
                .userId("best id ever")
                .postId("simple")
                .published(1491379422L)
                .build());

        MockHttpServletRequestBuilder builder = put("/comments/" + commentRepository.findAll().get(0).getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json);

        CommentResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), CommentResponse.class);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void save() throws Exception {
        String json = objectMapper.writeValueAsString(CommentRequest.builder()
                .text("oda")
                .userId("23")
                .postId("2020")
                .published(1491379422L)
                .comments(null)
                .build());

        MockHttpServletRequestBuilder builder = post("/comments")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json);

        CommentResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), CommentResponse.class);

        CommentResponse expected = CommentConverter.convert(Comment.builder()
                .id(actual.getId())
                .text("oda")
                .userId("23")
                .postId("2020")
                .published(1491379422L)
                .comments(null)
                .build());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteById() throws Exception {
        String id = commentRepository.findAll().get(0).getId();
        this.mockMvc.perform(delete("/comments/" + id))
                .andDo(print())
                .andExpect(status().isOk());

        Assertions.assertEquals(commentRepository.findAll().size(), 2);
    }
}
