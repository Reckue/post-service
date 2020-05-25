package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Node;
import com.reckue.post.repositories.NodeRepository;
import com.reckue.post.services.NodeService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Class NodeServiceRealization represents realization of NodeService.
 *
 * @author Kamila Meshcheryakova
 */
@RequiredArgsConstructor
public class NodeServiceRealization implements NodeService {

    private final NodeRepository nodeRepository;

    /**
     * This method is used to create an object of class Node.
     * Throws {@link ModelAlreadyExistsException} in case if such object already exists.
     * @param node object of class Node
     * @return node object of class Node
     */
    @Override
    public Node create(Node node) {
        if (!nodeRepository.existsById(node.getId())) {
            node.setId(UUID.randomUUID().toString());
            return nodeRepository.save(node);
        } else {
            throw new ModelAlreadyExistsException("Node already exists.");
        }
    }

    /**
     * This method is used to update data in an object of class Node.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     * @param node object of class Node
     * @return node object of class Node
     */
    @Override
    public Node update(Node node) {
        Node savedNode;
        if (node.getId() != null && nodeRepository.existsById(node.getId())) {
            savedNode = nodeRepository.findById(node.getId()).get();
            savedNode.setType(node.getType());
            savedNode.setContentId(node.getContentId());
            savedNode.setSource(node.getSource());
            savedNode.setStatus(node.getStatus());
            return nodeRepository.save(savedNode);
        } else {
            throw new ModelNotFoundException("Node not found by id " + node.getId() + ".");
        }
    }

    /**
     * This method is used to get all objects of class Node by parameters.
     * @param limit quantity of objects
     * @param offset quantity to skip
     * @param sort parameter for sorting
     * @param desc sorting descending
     * @return list of objects of class Node
     */
    @Override
    public List<Node> findAll(int limit, int offset, String sort, boolean desc) {
        return nodeRepository.findAll().stream()
                .limit(limit)
                .skip(offset)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     * @param id object
     * @return object of class Node
     */
    @Override
    public Node findById(String id) {
        return nodeRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException("Node not found by id " + id + "."));
    }

    /**
     * This method is used to delete an object by id.
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
