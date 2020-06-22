package com.reckue.post.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.models.Node;
import com.reckue.post.models.NodeType;
import com.reckue.post.models.StatusType;
import com.reckue.post.repositories.NodeRepository;
import com.reckue.post.transfers.NodeRequest;
import com.reckue.post.transfers.NodeResponse;
import com.reckue.post.transfers.PollNodeRequest;
import com.reckue.post.utils.converters.NodeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class NodeControllerIntTest is the integration type of test.
 *
 * @author Viktor Grigoriev
 */
@AutoConfigureMockMvc
public class NodeControllerIntTest extends PostServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostController nodeController;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        nodeRepository.deleteAll();

        nodeRepository.save(Node.builder()
                .id("1")
                .username("Alex")
                .build());
        nodeRepository.save(Node.builder()
                .id("2")
                .username("Sam")
                .build());
        nodeRepository.save(Node.builder()
                .id("3")
                .username("Bill")
                .build());
    }

    @Test
    public void load() {
        assertThat(nodeController).isNotNull();
        System.out.println(nodeRepository.findAll().size());
    }

    @Test
    public void findAllSortedByIdDesc() throws Exception {
        List<NodeResponse> expected = nodeRepository.findAll().stream()
                .map(NodeConverter::convert)
                .sorted(Comparator.comparing(NodeResponse::getId))
                .collect(Collectors.toList());

        Collections.reverse(expected);

        expected = expected.stream()
                .limit(2)
                .collect(Collectors.toList());

        List<NodeResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/nodes?desc=true&limit=2&offset=0&sort=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByIdAsc() throws Exception {
        List<NodeResponse> expected = nodeRepository.findAll().stream()
                .map(NodeConverter::convert)
                .limit(3)
                .collect(Collectors.toList());

        List<NodeResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/nodes?desc=false&limit=3&offset=0&sort=id"))
                .andDo(print()).andExpect(status()
                        .isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByUsernameAsc() throws Exception {
        List<NodeResponse> expected = nodeRepository.findAll().stream()
                .map(NodeConverter::convert)
                .sorted(Comparator.comparing(NodeResponse::getUsername))
                .limit(2)
                .collect(Collectors.toList());

        List<NodeResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/nodes?desc=false&limit=2&offset=0&sort=username"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllSortedByUsernameDesc() throws Exception {
        List<NodeResponse> expected = nodeRepository.findAll().stream()
                .map(NodeConverter::convert)
                .sorted(Comparator.comparing(NodeResponse::getUsername))
                .collect(Collectors.toList());

        Collections.reverse(expected);

        expected = expected.stream()
                .limit(2)
                .collect(Collectors.toList());

        List<NodeResponse> actual = objectMapper
                .readValue(this.mockMvc.perform(get("/nodes?desc=true&limit=2&offset=0&sort=username"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findById() throws Exception {
        NodeResponse expected = NodeConverter.convert(nodeRepository.findAll().get(0));

        NodeResponse actual = objectMapper.readValue(this.mockMvc.perform(get("/nodes/" + expected.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), NodeResponse.class);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update() throws Exception {
        NodeResponse expected = NodeConverter.convert(Node.builder()
                .id(nodeRepository.findAll().get(0).getId())
                .source("source")
                .type(NodeType.TEXT)
                .status(StatusType.ACTIVE)
                .build());

        String json = objectMapper.writeValueAsString(NodeRequest.builder()
                .source("source")
                .type(NodeType.TEXT)
                .status(StatusType.ACTIVE)
                .build());

        MockHttpServletRequestBuilder builder = put("/nodes/" + nodeRepository.findAll().get(0).getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json);

        NodeResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), NodeResponse.class);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void save() throws Exception {
        String json = objectMapper.writeValueAsString(NodeRequest.builder()
                .source("source")
                .type(NodeType.TEXT)
                .status(StatusType.ACTIVE)
                .build());

        MockHttpServletRequestBuilder builder = post("/nodes")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json);

        NodeResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), NodeResponse.class);

        NodeResponse expected = NodeConverter.convert(Node.builder()
                .id(actual.getId())
                .source("source")
                .type(NodeType.TEXT)
                .status(StatusType.ACTIVE)
                .build());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void saveWithPollNode() throws Exception {
        String json = objectMapper.writeValueAsString(NodeRequest.builder()
                .content(PollNodeRequest.builder().
                        items(List.of("Katya", "Olya"))
                .build())
                .source("source")
                .type(NodeType.TEXT)
                .status(StatusType.ACTIVE)
                .build());

        MockHttpServletRequestBuilder builder = post("/nodes")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json);

        NodeResponse actual = objectMapper.readValue(this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString(), NodeResponse.class);

        NodeResponse expected = NodeConverter.convert(Node.builder()
                .id(actual.getId())
                .content(PollNodeRequest.builder().
                        items(List.of("Katya", "Olya"))
                        .build())
                .source("source")
                .type(NodeType.TEXT)
                .status(StatusType.ACTIVE)
                .build());

        Assertions.assertEquals(expected, actual);
    }



    @Test
    void deleteById() throws Exception {
        String id = nodeRepository.findAll().get(0).getId();
        this.mockMvc.perform(delete("/nodes/" + id))
                .andDo(print())
                .andExpect(status().isOk());

        Assertions.assertEquals(nodeRepository.findAll().size(), 2);
    }
}
