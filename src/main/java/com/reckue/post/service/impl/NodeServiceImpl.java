package com.reckue.post.service.impl;

import com.reckue.post.exception.ReckueAccessDeniedException;
import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.exception.model.comment.CommentNotFoundException;
import com.reckue.post.exception.model.node.NodeNotFoundException;
import com.reckue.post.exception.model.post.PostNotFoundException;
import com.reckue.post.model.Node;
import com.reckue.post.model.Role;
import com.reckue.post.model.type.ParentType;
import com.reckue.post.repository.CommentRepository;
import com.reckue.post.repository.NodeRepository;
import com.reckue.post.repository.PostRepository;
import com.reckue.post.service.NodeService;
import com.reckue.post.util.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class NodeServiceImpl represents realization of NodeService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    //@NotNullArgs //??
    public Node create(Node node) {
        node.setUserId(CurrentUser.getId());
        validateCreatingNode(node);
        // todo: add status validation
        return nodeRepository.save(node);
    }

    public void validateCreatingNode(Node node) {
        ParentType parentType = node.getParentType();
        switch (parentType) {
            case POST:
                if (!postRepository.existsById(node.getParentId())) {
                    throw new PostNotFoundException(node.getParentId());
                }
                break;
            case COMMENT:
                if (!commentRepository.existsById(node.getParentId())) {
                    throw new CommentNotFoundException(node.getParentId());
                }
                break;
        }
    }

    @Override
    public Node update(Node node) {
        if (node.getId() == null) {
            throw new ReckueIllegalArgumentException("The parameter is null");
        }

        Node savedNode = nodeRepository
                .findById(node.getId())
                .orElseThrow(() -> new NodeNotFoundException(node.getId()));
        savedNode.setType(node.getType());
        //FIXME we don't change content of concrete node - for TEXT node it's "content" - must be correct!
        // now content of savedNode is the same how it was, it can't save the content of node
        savedNode.setParentType(node.getParentType());
        savedNode.setSource(node.getSource());
        savedNode.setStatus(node.getStatus());

        return nodeRepository.save(savedNode);
    }

    @Override
    public List<Node> findAll() {
        return nodeRepository.findAll();
    }

    @Override
    public List<Node> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
        if (limit == null) limit = 10;
        if (offset == null) offset = 0;
        if (StringUtils.isEmpty(sort)) sort = "id";
        if (desc == null) desc = false;

        if (limit < 0 || offset < 0) {
            throw new ReckueIllegalArgumentException("Limit or offset is incorrect");
        }
        return findAllByTypeAndDesc(sort, desc).stream()
                .limit(limit)
                .skip(offset)
                .collect(Collectors.toList());
    }

    public List<Node> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<Node> nodes = findAllBySortType(sort);
            Collections.reverse(nodes);
            return nodes;
        }
        return findAllBySortType(sort);
    }

    public List<Node> findAllBySortType(String sort) {
        switch (sort) {
            case "type":
                return findAllAndSortByType();
            case "status":
                return findAllAndSortByStatus();
            case "source":
                return findAllAndSortBySource();
            case "createdDate":
                return findAllAndSortByCreatedDate();
            case "modificationDate":
                return findAllAndSortByModificationDate();
            case "userId":
                return findAllAndSortByUserId();
            case "id":
                return findAllAndSortById();
            default:
                throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
        }
    }

    private List<Node> findAllAndSortByModificationDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getModificationDate))
                .collect(Collectors.toList());
    }

    public List<Node> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getId))
                .collect(Collectors.toList());
    }

    public List<Node> findAllAndSortByStatus() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getStatus))
                .collect(Collectors.toList());
    }

    public List<Node> findAllAndSortByType() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getType))
                .collect(Collectors.toList());
    }

    public List<Node> findAllAndSortByUserId() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getUserId))
                .collect(Collectors.toList());
    }

    public List<Node> findAllAndSortBySource() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getSource))
                .collect(Collectors.toList());
    }

    public List<Node> findAllAndSortByCreatedDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Node::getCreatedDate))
                .collect(Collectors.toList());
    }

    @Override
    public Node findById(String id) {
        return nodeRepository.findById(id).orElseThrow(
                () -> new NodeNotFoundException(id));
    }

    @Override
    public void deleteById(String id) {
        if (!nodeRepository.existsById(id)) {
            throw new NodeNotFoundException(id);
        }
        Optional<Node> node = nodeRepository.findById(id);
        if (node.isPresent()) {
            String nodeUser = node.get().getUserId();
            if (CurrentUser.getId().equals(nodeUser) || CurrentUser.getRoles().contains(Role.ADMIN)) {
                nodeRepository.deleteById(id);
            } else {
                throw new ReckueAccessDeniedException("The operation is forbidden");
            }
        }

    }

}
