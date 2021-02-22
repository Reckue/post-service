//package com.reckue.post.controller;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.reckue.post.PostServiceApplicationTests;
//import com.reckue.post.generated.models.TagRequest;
//import com.reckue.post.generated.models.TagResponse;
//import com.reckue.post.model.Tag;
//import com.reckue.post.repository.TagRepository;
//import com.reckue.post.util.converter.TagConverter;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Class TagControllerIntTest is the integration type of test.
// *
// * @author Daria Smirnova
// */
//@AutoConfigureMockMvc
//public class TagControllerIntTest extends PostServiceApplicationTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TagController tagController;
//
//    @Autowired
//    private TagRepository tagRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        tagRepository.deleteAll();
//
//        tagRepository.save(Tag.builder()
//                .id("1")
//                .name("Alex")
//                .build());
//        tagRepository.save(Tag.builder()
//                .id("2")
//                .name("Sam")
//                .build());
//        tagRepository.save(Tag.builder()
//                .id("3")
//                .name("Bill")
//                .build());
//    }
//
//    @Test
//    public void load() {
//        assertThat(tagController).isNotNull();
//        System.out.println(tagRepository.findAll().size());
//    }
//
//    @Test
//    public void findAllSortedByIdDesc() throws Exception {
//        List<TagResponse> expected = tagRepository.findAll().stream()
//                .map(TagConverter::convert)
//                .sorted(Comparator.comparing(TagResponse::getId).reversed())
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<TagResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/tags?desc=true&limit=2&offset=0&sort=id"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByIdAsc() throws Exception {
//        List<TagResponse> expected = tagRepository.findAll().stream()
//                .map(TagConverter::convert)
//                .limit(3)
//                .collect(Collectors.toList());
//
//        List<TagResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/tags?desc=false&limit=3&offset=0&sort=id"))
//                        .andDo(print()).andExpect(status()
//                                .isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findAllSortedByNameAsc() throws Exception {
//        List<TagResponse> expected = tagRepository.findAll().stream()
//                .map(TagConverter::convert)
//                .sorted(Comparator.comparing(TagResponse::getName))
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<TagResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/tags?desc=false&limit=2&offset=0&sort=name"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertTrue(Arrays.deepEquals(expected.toArray(), actual.toArray()));
//    }
//
//    @Test
//    public void findAllSortedByNameDesc() throws Exception {
//        List<TagResponse> expected = tagRepository.findAll().stream()
//                .map(TagConverter::convert)
//                .sorted(Comparator.comparing(TagResponse::getName).reversed())
//                .limit(2)
//                .collect(Collectors.toList());
//
//        List<TagResponse> actual = objectMapper
//                .readValue(this.mockMvc.perform(get("/tags?desc=true&limit=2&offset=0&sort=name"))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse().getContentAsString(), new TypeReference<>() {
//                });
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void findById() throws Exception {
//        TagResponse expected = TagConverter.convert(tagRepository.findAll().get(0));
//
//        TagResponse actual = objectMapper.readValue(this.mockMvc.perform(get("/tags/" + expected.getId()))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse().getContentAsString(), TagResponse.class);
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void update() throws Exception {
//        TagResponse expected = TagConverter.convert(Tag.builder()
//                .id(tagRepository.findAll().get(0).getId())
//                .name("Alex")
//                .build());
//
//        String json = objectMapper.writeValueAsString(TagRequest.builder()
//                .name("Alex")
//                .build());
//
//        MockHttpServletRequestBuilder builder = put("/tags/" + tagRepository.findAll().get(0).getId())
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(json);
//
//        TagResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse().getContentAsString(), TagResponse.class);
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void save() throws Exception {
//        String json = objectMapper.writeValueAsString(TagRequest.builder()
//                .name("Alex")
//                .build());
//
//        MockHttpServletRequestBuilder builder = post("/tags")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(json);
//
//        TagResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse().getContentAsString(), TagResponse.class);
//
//        TagResponse expected = TagConverter.convert(Tag.builder()
//                .id(actual.getId())
//                .name("Alex")
//                .build());
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void deleteById() throws Exception {
//        String id = tagRepository.findAll().get(0).getId();
//        this.mockMvc.perform(delete("/tags/" + id))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        Assertions.assertEquals(tagRepository.findAll().size(), 2);
//    }
//}
