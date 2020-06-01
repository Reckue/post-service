package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Node;
import com.reckue.post.repositories.NodeRepository;
import com.reckue.post.services.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Class NodeServiceRealization represents realization of NodeService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class NodeServiceRealization implements NodeService {

    private final NodeRepository nodeRepository;

    /**
     * This method is used to create an object of class Node.
     * Throws {@link ModelAlreadyExistsException} in case if such object already exists.
     *
     * @param node object of class Node
     * @return node object of class Node
     */
    @Override
    public Node create(Node node) {
        node.setId(UUID.randomUUID().toString());
        if (!nodeRepository.existsById(node.getId())) {
            return nodeRepository.save(node);
        } else {
            throw new ModelAlreadyExistsException("Node already exists.");
        }
    }

    /**
     * This method is used to update data in an object of class Node.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link IllegalArgumentException} in case
     * if parameter equals null.
     *
     * @param node object of class Node
     * @return node object of class Node
     */
    @Override
    public Node update(Node node) {
        if (node.getId() == null) {
            throw new IllegalArgumentException("The parameter is null.");
        }
        if (!nodeRepository.existsById(node.getId())) {
            throw new ModelNotFoundException("Node not found by id " + node.getId() + ".");
        }
        Node savedNode = Node.builder()
                .id(node.getId())
                .username(node.getUsername())
                .type(node.getType())
                .contentId(node.getContentId())
                .source(node.getSource())
                .status(node.getStatus())
                .published(node.getPublished())
                .build();
        return nodeRepository.save(savedNode);
    }

    /**
     * This method is used to get all objects of class Node.
     *
     * @return list of objects of class Node
     */
    @Override
    public List<Node> findAll() {
        return nodeRepository.findAll();
    }

    /**
     * This method is used to get all objects of class Node by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of given quantity of objects of class Node with a given offset
     * sorted by the selected parameter for sorting in descending order
     */
    @Override
    public List<Node> findAll(int limit, int offset, String sort, boolean desc) {
        return findAllByTypeAndDesc(sort, desc).stream()
                .limit(limit)
                .skip(offset)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects in descending order by type.
     *
     * @param sort parameter for sorting
     * @param desc sorting descending
     * @return list of objects of class Node sorted by the selected parameter for sorting
     * in descending order
     */
    public List<Node> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<Node> nodes = findAllBySortType(sort);
            Collections.reverse(nodes);
            return nodes;
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: type, status, source, published, default - id
     * @return list of objects of class Node sorted by the selected parameter for sorting
     */
    public List<Node> findAllBySortType(String sort) {
        switch (sort) {
            case "type":
                return findAllAndSortByType();
            case "status":
                return findAllAndSortByStatus();
            case "source":
                return findAllAndSortBySource();
            case "published":
                return findAllAndSortByPublished();
            case "username":
                return findAllAndSortByUsername();
            case "id":
                return findAllAndSortById();
        }
        throw new IllegalArgumentException("Such field as" + sort + " doesn't exist.");
    }

    /**
     * This method is used to sort objects by id.
     *
     * @return list of objects of class Node sorted by id
     */
    public List<Node> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by status.
     *
     * @return list of objects of class Node sorted by status
     */
    public List<Node> findAllAndSortByStatus() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getStatus))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by type.
     *
     * @return list of objects of class Node sorted by type
     */
    public List<Node> findAllAndSortByType() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getType))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by username.
     *
     * @return list of objects of class Node sorted by username
     */
    public List<Node> findAllAndSortByUsername() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getUsername))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by source.
     *
     * @return list of objects of class Node sorted by source
     */
    public List<Node> findAllAndSortBySource() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getSource))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by publication date.
     *
     * @return list of objects of class Node sorted by publication date
     */
    public List<Node> findAllAndSortByPublished() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getPublished))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return post object of class Node
     */
    @Override
    public Node findById(String id) {
        return nodeRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException("Node not found by id " + id + "."));
    }

    /**
     * This method is used to delete an object by id.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     */
    @Override
    public void deleteById(String id) {
        if (nodeRepository.existsById(id)) {
            nodeRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("Node not found by id " + id + ".");
        }
    }
}
