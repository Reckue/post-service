package com.reckue.post.service.impl;

import com.reckue.post.model.Node;
import com.reckue.post.model.type.StatusType;
import com.reckue.post.repository.NodeRepository;
import com.reckue.post.service.NodeService;
import com.reckue.post.service.impl.validation.NodeValidationServiceImpl;
import com.reckue.post.util.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.reckue.post.model.Role.ADMIN;
import static com.reckue.post.model.Role.MODERATOR;

@Service
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;
    private final NodeValidationServiceImpl nodeValidationService;

    @Transactional
    @Override
    public Node create(Node node) {
        nodeValidationService.validateNodeStatusOnCreate(node);
        return Optional.ofNullable(node).map(nodeToStore -> {
            nodeToStore.setUserId(CurrentUser.getId());
            nodeToStore.setStatus(StatusType.ACTIVE);
            nodeToStore.setCreatedDate(LocalDateTime.now());
            nodeToStore.setModificationDate(LocalDateTime.now());
            return nodeRepository.save(nodeToStore);
        }).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    @Override
    public Node update(Node node) {
        nodeValidationService.validateNodeStatusOnUpdate(node, node.getStatus());
        return nodeRepository.findById(node.getId()).map(storedNode -> {
            storedNode.setStatus(node.getStatus());
            storedNode.setUserId(CurrentUser.getId());
            storedNode.setType(node.getType());
            storedNode.setContent(node.getContent());
            storedNode.setModificationDate(LocalDateTime.now());
            return nodeRepository.save(storedNode);
        }).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Page<Node> findAll() {
        return findAll(10, 0, "id", true);
    }

    @Override
    public Page<Node> findAll(Integer limit, Integer offset, String sortProperty, Boolean isDesc) {
        Sort sortBy = isDesc ? Sort.by(sortProperty).descending() : Sort.by(sortProperty).ascending();
        return nodeRepository.findAll(PageRequest.of(offset, limit, sortBy));
    }

    @Override
    public Node findById(String nodeId) {
        return nodeRepository.findById(nodeId).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    @Override
    public void deleteById(String nodeId) {
        if (nodeRepository.existsById(nodeId)) {
            Optional<Node> node = nodeRepository.findById(nodeId);

            node.ifPresent(nodeToUpdate -> {
                if (CurrentUser.getId().equals(nodeToUpdate.getUserId())
                        || (CurrentUser.getRoles().contains(MODERATOR) || CurrentUser.getRoles().contains(ADMIN))) {
                    nodeToUpdate.setStatus(StatusType.DELETED);
                    nodeRepository.save(nodeToUpdate);
                }
            });
        } else {
            throw new NoSuchElementException();
        }
    }

}
