package com.reckue.post.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.models.Tag;
import com.reckue.post.repositories.TagRepository;
import com.reckue.post.transfers.TagResponse;
import com.reckue.post.utils.converters.TagConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("staging")
@AutoConfigureMockMvc
public class TagControllerIntegrationTest extends PostServiceApplicationTests {

    @Autowired
    private TagController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void load() {
        assertThat(controller).isNotNull();
    }

    @BeforeEach
    public void setUp() {
        tagRepository.save(Tag.builder()
                .id("1")
                .name("java")
                .build());

        tagRepository.save(Tag.builder()
                .id("2")
                .name("python")
                .build());

        tagRepository.save(Tag.builder()
                .id("3")
                .name("cpp")
                .build());
    }

    @Test
    public void findById() throws Exception {
        TagResponse expected = TagConverter.convert(tagRepository.findAll().get(0));

        TagResponse actual = objectMapper.readValue(this.mockMvc.perform(get("/tags/" + expected.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), TagResponse.class);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAll() throws Exception {
        List<TagResponse> expected = tagRepository.findAll().stream()
                .map(TagConverter::convert)
                .collect(Collectors.toList());

        Collections.reverse(expected);

        expected = expected.stream()
                .limit(2)
                .collect(Collectors.toList());

        List<TagResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/tags?desc=true&limit=2&offset=0&sort=id"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(), new TypeReference<>() {});

        Assertions.assertEquals(expected, actual);
    }
}