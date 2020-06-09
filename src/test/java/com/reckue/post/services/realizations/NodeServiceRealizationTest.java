package com.reckue.post.services.realizations;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Node;
import com.reckue.post.models.Post;
import com.reckue.post.repositories.NodeRepository;
import com.reckue.post.transfers.NodeResponse;
import com.reckue.post.utils.converters.NodeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

class NodeServiceRealizationTest extends PostServiceApplicationTests {

    @Mock
    private NodeRepository nodeRepository;

    @InjectMocks
    private NodeServiceRealization nodeService;

    @Test
    public void findAll() {
        Node node1 = Node.builder().id("1").username("moon").build();
        Node node2 = Node.builder().id("2").username("eclipse").build();


        List<Node> nodes = Stream.of(node1, node2).collect(Collectors.toList());

        when(nodeRepository.findAll()).thenReturn(nodes);

        Assertions.assertEquals(nodes, nodeService.findAll());
    }

    @Test
    public void create() {
        Node node = Node.builder().id("1").username("moon").build();
        when(nodeRepository.save(node)).thenReturn(node);

        Assertions.assertEquals(node, nodeService.create(node));
    }

    @Test
    public void createIfExists() {
        Node node = Node.builder()
                .id("1")
                .username("node")
                .build();
        when(nodeRepository.save(node)).thenReturn(node);

        Assertions.assertEquals(node, nodeService.create(node));
    }

    @Test
    public void update() {
        Node nodeOne = Node.builder()
                .id("1")
                .username("nodeOne")
                .build();
        when(nodeRepository.existsById(nodeOne.getId())).thenReturn(true);
        when(nodeRepository.save(nodeOne)).thenReturn(nodeOne);
        Assertions.assertEquals(nodeOne, nodeService.update(nodeOne));
    }

    @Test
    public void updateWithExistId() {
        Node nodeOne = Node.builder()
                .id("1")
                .username("nodeOne")
                .build();
        when(nodeRepository.existsById(nodeOne.getId())).thenReturn(false);
        when(nodeRepository.save(nodeOne)).thenReturn(nodeOne);
        Assertions.assertThrows(ModelNotFoundException.class, () -> nodeService.update(nodeOne));
    }

    @Test
    public void findByIdWithException() {
        Node nodeOne = Node.builder()
                .id("1")
                .username("nodeOne")
                .build();
        when(nodeRepository.findById(nodeOne.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(ModelNotFoundException.class, () -> nodeService.findById(nodeOne.getId()));
    }

    @Test
    public void deleteById() {
        Node nodeOne = Node.builder()
                .id("1")
                .username("nodeOne")
                .build();
        when(nodeRepository.existsById(nodeOne.getId())).thenReturn(false);
        Assertions.assertThrows(ModelNotFoundException.class, () -> nodeService.deleteById(nodeOne.getId()));
    }

    @Test
    public void deleteByIdWithException() {
        Node nodeOne = Node.builder()
                .id("1")
                .username("nodeOne")
                .build();
        when(nodeRepository.existsById(nodeOne.getId())).thenReturn(false);
        Assertions.assertThrows(ModelNotFoundException.class, () -> nodeService.deleteById(nodeOne.getId()));
    }
}

