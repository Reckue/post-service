package com.reckue.post.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.model.Post;
import com.reckue.post.model.Rating;
import com.reckue.post.repository.PostRepository;
import com.reckue.post.repository.RatingRepository;
import com.reckue.post.transfer.dto.RatingRequest;
import com.reckue.post.transfer.dto.RatingResponse;
import com.reckue.post.util.converter.RatingConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Class RatingControllerIntTest is the integration type of test.
 *
 * @author Kamila Meshcheryakova
 */
@AutoConfigureMockMvc
public class RatingControllerIntTest extends PostServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RatingController ratingController;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        ratingRepository.deleteAll();

        ratingRepository.save(Rating.builder()
                .id("1")
                .userId("Garry")
                .postId("cars")
                .createdDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(1234567890),
                        TimeZone.getDefault().toZoneId()))
                .build());
        ratingRepository.save(Rating.builder()
                .id("2")
                .userId("Antony")
                .postId("animals")
                .createdDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(1234563590),
                        TimeZone.getDefault().toZoneId()))
                .build());
        ratingRepository.save(Rating.builder()
                .id("3")
                .userId("Bart")
                .postId("news")
                .createdDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(2134567820),
                        TimeZone.getDefault().toZoneId()))
                .build());
    }

    @Test
    public void load() {
        assertThat(ratingController).isNotNull();
    }

    @Test
    public void findAllSortedByIdAsc() throws Exception {
        List<RatingResponse> expected = ratingRepository.findAll().stream()
                .map(RatingConverter::convert)
                .limit(3)
                .peek(System.out::println)
                .collect(Collectors.toList());

        List<RatingResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/rating?desc=false&limit=3&offset=0&sort=id"))
                        .andDo(print()).andExpect(status()
                                .isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByIdDesc() throws Exception {
        List<RatingResponse> expected = ratingRepository.findAll().stream()
                .map(RatingConverter::convert)
                .sorted(Comparator.comparing(RatingResponse::getId).reversed())
                .limit(2)
                .collect(Collectors.toList());

        List<RatingResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/rating?desc=true&limit=2&offset=0&sort=id"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByPublishedAsc() throws Exception {
        List<RatingResponse> expected = ratingRepository.findAll().stream()
                .map(RatingConverter::convert)
                .sorted(Comparator.comparing(RatingResponse::getCreatedDate))
                .limit(2)
                .collect(Collectors.toList());

        List<RatingResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/rating?desc=false&limit=2&offset=0&sort=createdDate"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByPublishedDesc() throws Exception {
        List<RatingResponse> expected = ratingRepository.findAll().stream()
                .map(RatingConverter::convert)
                .sorted(Comparator.comparing(RatingResponse::getCreatedDate).reversed())
                .limit(2)
                .collect(Collectors.toList());

        List<RatingResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/rating?desc=true&limit=2&offset=0&sort=createdDate"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertEquals(expected, actual);
    }

    @Disabled
    void update() throws Exception {
        String json = objectMapper.writeValueAsString(RatingRequest.builder()
                .postId(ratingRepository.findAll().get(0).getPostId())
                .build());

        MockHttpServletRequestBuilder builder = put("/rating/" + ratingRepository.findAll().get(0).getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json);

        RatingResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), RatingResponse.class);

        RatingResponse expected = RatingResponse.builder()
                .id(ratingRepository.findAll().get(0).getId())
                .userId(ratingRepository.findAll().get(0).getUserId())
                .postId(ratingRepository.findAll().get(0).getPostId())
                .build();

        Assertions.assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getUserId(), actual.getUserId()),
                () -> assertEquals(expected.getPostId(), actual.getPostId()));
    }

    @Disabled
    void save() throws Exception {
        Post post = Post.builder()
                .id("2020")
                .build();
        postRepository.save(post);
        String json = objectMapper.writeValueAsString(RatingRequest.builder()
                .postId(post.getId())
                .build());

        MockHttpServletRequestBuilder builder = post("/rating")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json);

        RatingResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), RatingResponse.class);

        RatingResponse expected = RatingResponse.builder()
                .id(actual.getId())
                .userId("23")
                .postId(post.getId())
                .build();

        Assertions.assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getUserId(), actual.getUserId()),
                () -> assertEquals(expected.getPostId(), actual.getPostId()));
    }

    @Disabled
    void notSaveNotFoundPost() throws Exception {

        String json = objectMapper.writeValueAsString(RatingRequest.builder()
                .userId("23")
                .postId("2020")
                .build());

        MockHttpServletRequestBuilder builder = post("/rating")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json);

        RatingResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse().getContentAsString(), RatingResponse.class);

        RatingResponse expected = RatingResponse.builder().build();

        Assertions.assertEquals(expected, actual);
    }

    @Disabled
    void deleteById() throws Exception {
        String id = ratingRepository.findAll().get(0).getId();
        this.mockMvc.perform(delete("/rating/" + id))
                .andDo(print())
                .andExpect(status().isOk());

        Assertions.assertEquals(ratingRepository.findAll().size(), 2);
    }
}
