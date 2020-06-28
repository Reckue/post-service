//package com.reckue.post.services.realizations;
//
//import com.reckue.post.PostServiceApplicationTests;
//import com.reckue.post.exceptions.ModelAlreadyExistsException;
//import com.reckue.post.exceptions.ModelNotFoundException;
//import com.reckue.post.models.Node;
//import com.reckue.post.models.types.StatusType;
//import com.reckue.post.repositories.NodeRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
///**
// * Class NodeServiceRealizationTest represents test for NodeService class.
// *
// * @author Iveri Narozashvili
// */
//public class NodeServiceRealizationTest extends PostServiceApplicationTests {
//
//    @Mock
//    private NodeRepository nodeRepository;
//
//    @InjectMocks
//    private NodeServiceRealization nodeService;
//
//    @Test
//    public void findAll() {
//        Node node1 = Node.builder()
//                .id("1")
//                .username("moon")
//                .build();
//        Node node2 = Node.builder()
//                .id("2")
//                .username("eclipse")
//                .build();
//        List<Node> nodes = Stream.of(node1, node2).collect(Collectors.toList());
//        when(nodeRepository.findAll()).thenReturn(nodes);
//
//        assertEquals(nodes, nodeService.findAll());
//    }
//
//    @Test
//    public void create() {
//        Node node = Node.builder()
//                .id("1")
//                .username("moon")
//                .build();
//        when(nodeRepository.save(node)).thenReturn(node);
//
//        assertEquals(node, nodeService.create(node));
//    }
//
//    @Test
//    public void createIfExists() {
//        Node node = Node.builder()
//                .id("1")
//                .username("node")
//                .build();
//        doReturn(true).when(nodeRepository).existsById(Mockito.anyString());
//        Exception exception = assertThrows(ModelAlreadyExistsException.class, () -> nodeService.create(node));
//
//        assertEquals("Node already exists", exception.getMessage());
//    }
//
//    @Test
//    public void update() {
//        Node nodeOne = Node.builder()
//                .id("1")
//                .username("nodeOne")
//                .build();
//        when(nodeRepository.existsById(nodeOne.getId())).thenReturn(true);
//        when(nodeRepository.save(nodeOne)).thenReturn(nodeOne);
//
//        assertEquals(nodeOne, nodeService.update(nodeOne));
//    }
//
//    @Test
//    public void updateWithNullId() {
//        Node nodeOne = Node.builder()
//                .username("nodeOne")
//                .build();
//
//        assertThrows(IllegalArgumentException.class, () -> nodeService.update(nodeOne));
//    }
//
//    @Test
//    public void updateWithExistId() {
//        Node nodeOne = Node.builder()
//                .id("1")
//                .username("nodeOne")
//                .build();
//        when(nodeRepository.existsById(nodeOne.getId())).thenReturn(false);
//        when(nodeRepository.save(nodeOne)).thenReturn(nodeOne);
//
//        assertThrows(ModelNotFoundException.class, () -> nodeService.update(nodeOne));
//    }
//
//    @Test
//    public void findByIdWithException() {
//        Node nodeOne = Node.builder()
//                .id("1")
//                .username("nodeOne")
//                .build();
//        when(nodeRepository.findById(nodeOne.getId())).thenReturn(Optional.empty());
//
//        assertThrows(ModelNotFoundException.class, () -> nodeService.findById(nodeOne.getId()));
//    }
//
//    @Test
//    public void findById() {
//        Node nodeOne = Node.builder()
//                .id("1")
//                .username("nodeOne")
//                .build();
//        when(nodeRepository.findById(nodeOne.getId())).thenReturn(Optional.of(nodeOne));
//
//        assertEquals(nodeOne, nodeService.findById(nodeOne.getId()));
//    }
//
//    @Test
//    public void findAllSortById() {
//        Node nodeOne = Node.builder().id("1").build();
//        Node nodeTwo = Node.builder().id("2").build();
//        Node nodeThree = Node.builder().id("3").build();
//        List<Node> nodes = List.of(nodeOne, nodeTwo, nodeThree);
//
//        when(nodeRepository.findAll()).thenReturn(nodes);
//
//        List<Node> expected = nodes.stream()
//                .sorted(Comparator.comparing(Node::getId))
//                .collect(Collectors.toList());
//
//        assertEquals(expected, nodeService.findAllAndSortById());
//    }
//
//    @Test
//    public void findAllSortBySource() {
//        Node nodeOne = Node.builder().source("sourceOne").build();
//        Node nodeTwo = Node.builder().source("sourceTwo").build();
//        Node nodeThree = Node.builder().source("sourceThree").build();
//        List<Node> nodes = List.of(nodeOne, nodeTwo, nodeThree);
//
//        when(nodeRepository.findAll()).thenReturn(nodes);
//
//        List<Node> expected = nodes.stream()
//                .sorted(Comparator.comparing(Node::getSource))
//                .collect(Collectors.toList());
//
//        assertEquals(expected, nodeService.findAllAndSortBySource());
//    }
//
//    @Test
//    public void findAllSortByUsername() {
//        Node nodeOne = Node.builder().username("Max").build();
//        Node nodeTwo = Node.builder().username("Will").build();
//        Node nodeThree = Node.builder().username("Arny").build();
//        List<Node> nodes = List.of(nodeOne, nodeTwo, nodeThree);
//
//        when(nodeRepository.findAll()).thenReturn(nodes);
//
//        List<Node> expected = nodes.stream()
//                .sorted(Comparator.comparing(Node::getUsername))
//                .collect(Collectors.toList());
//
//        assertEquals(expected, nodeService.findAllAndSortByUsername());
//    }
//
//    @Test
//    public void findAllSortByPublished() {
//        Node nodeOne = Node.builder().published(1).build();
//        Node nodeTwo = Node.builder().published(2).build();
//        Node nodeThree = Node.builder().published(3).build();
//        List<Node> posts = List.of(nodeOne, nodeTwo, nodeThree);
//
//        when(nodeRepository.findAll()).thenReturn(posts);
//
//        List<Node> expected = posts.stream()
//                .sorted(Comparator.comparing(Node::getPublished))
//                .collect(Collectors.toList());
//
//        assertEquals(expected, nodeService.findAllAndSortByPublished());
//    }
//
//
//    @Test
//    public void findAllSortByStatus() {
//        Node nodeOne = Node.builder().status(StatusType.ACTIVE).build();
//        Node nodeTwo = Node.builder().status(StatusType.BANNED).build();
//        Node nodeThree = Node.builder().status(StatusType.DELETED).build();
//        List<Node> nodes = List.of(nodeOne, nodeTwo, nodeThree);
//
//        when(nodeRepository.findAll()).thenReturn(nodes);
//
//        List<Node> expected = nodes.stream()
//                .sorted(Comparator.comparing(Node::getStatus))
//                .collect(Collectors.toList());
//
//        assertEquals(expected, nodeService.findAllAndSortByStatus());
//    }
//
//    @Test
//    public void findAllSortByTypeAndDesc() {
//        Node nodeOne = Node.builder().username("Max").build();
//        Node nodeTwo = Node.builder().username("Will").build();
//        Node nodeThree = Node.builder().username("Arny").build();
//        List<Node> nodes = List.of(nodeOne, nodeTwo, nodeThree);
//
//        when(nodeRepository.findAll()).thenReturn(nodes);
//        List<Node> expected = nodes.stream()
//                .sorted(Comparator.comparing(Node::getUsername).reversed())
//                .collect(Collectors.toList());
//
//        assertEquals(expected, nodeService.findAllByTypeAndDesc("username", true));
//    }
//
//    @Test
//    public void deleteById() {
//        Node nodeOne = Node.builder()
//                .id("1")
//                .username("nodeOne")
//                .build();
//        List<Node> nodes = new ArrayList<>();
//        nodes.add(nodeOne);
//        when(nodeRepository.existsById(nodeOne.getId())).thenReturn(true);
//        doAnswer(invocation -> {
//            nodes.remove(nodeOne);
//            return null;
//        }).when(nodeRepository).deleteById(nodeOne.getId());
//        nodeService.deleteById(nodeOne.getId());
//
//        assertEquals(0, nodes.size());
//    }
//
//    @Test
//    public void deleteByIdWithException() {
//        Node nodeOne = Node.builder()
//                .id("1")
//                .username("nodeOne")
//                .build();
//        when(nodeRepository.existsById(nodeOne.getId())).thenReturn(false);
//
//        assertThrows(ModelNotFoundException.class, () -> nodeService.deleteById(nodeOne.getId()));
//    }
//}
