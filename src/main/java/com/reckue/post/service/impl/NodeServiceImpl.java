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

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;
    private final NodeValidationServiceImpl nodeValidationService;

    @Override
    public Node create(Node node) {
        return Optional.ofNullable(node).map(storedNode -> {
            storedNode.setUserId(CurrentUser.getId());
            storedNode.setStatus(StatusType.ACTIVE);
            storedNode.setCreatedDate(LocalDateTime.now());
            storedNode.setModificationDate(LocalDateTime.now());
            return nodeRepository.save(storedNode);
        }).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Node update(Node node) {
        return nodeRepository.findById(node.getId()).map(storedNode -> {
            nodeValidationService.validateNodeStatusOnUpdate(storedNode, node.getStatus());
            storedNode.setUserId(CurrentUser.getId());
            storedNode.setType(node.getType());
            storedNode.setContent(node.getContent());
            storedNode.setModificationDate(LocalDateTime.now());
            return nodeRepository.save(storedNode);
        }).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Page<Node> findAll() {
        return nodeRepository.findAll(PageRequest.of(0, 10, Sort.Direction.DESC));
    }

    @Override
    public Page<Node> findAll(Integer limit, Integer offset, String sortProperty, Boolean isDesc) {
        Sort sortBy = isDesc ? Sort.by(sortProperty).descending() : Sort.by(sortProperty).ascending();
        return nodeRepository.findAll(PageRequest.of(offset, limit, sortBy));
    }

    @Override
    public Node findById(String id) {
        return nodeRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(String id) {
        // TODO: fix
        nodeRepository.deleteById(id);
    }

}
