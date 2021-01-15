package com.reckue.post.service.impl;

import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.exception.model.node.pollnode.PollNodeNotFoundException;
import com.reckue.post.model.node.PollNode;
import com.reckue.post.processor.notnull.NotNullArgs;
import com.reckue.post.repository.PollNodeRepository;
import com.reckue.post.service.PollNodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class PollNodeServiceImpl represents realization of PollNodeService.
 *
 * @author Grigoriev Viktor
 */
@Service
@RequiredArgsConstructor
public class PollNodeServiceImpl implements PollNodeService {

    private final PollNodeRepository pollNodeRepository;

    @Override
    @NotNullArgs
    public PollNode create(PollNode node) {
        return pollNodeRepository.save(node);
    }

    @Override
    public PollNode update(PollNode node) {
        if (node.getId() == null) {
            throw new ReckueIllegalArgumentException("The parameter is null");
        }
        PollNode savedPollNode = pollNodeRepository
                .findById(node.getId())
                .orElseThrow(() -> new PollNodeNotFoundException(node.getId()));
        savedPollNode.setTitle(node.getTitle());
        savedPollNode.setItems(node.getItems());
        return pollNodeRepository.save(savedPollNode);
    }

    @Override
    public List<PollNode> findAll() {
        return pollNodeRepository.findAll();
    }

    @Override
    public List<PollNode> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
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

    public List<PollNode> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<PollNode> node = findAllBySortType(sort);
            Collections.reverse(node);
            return node;
        }
        return findAllBySortType(sort);
    }

    public List<PollNode> findAllBySortType(String sort) {

        switch (sort) {
            case "title":
                return findAllAndSortByTitle();
            case "id":
                return findAllAndSortById();
        }
        throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
    }

    public List<PollNode> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(PollNode::getId))
                .collect(Collectors.toList());
    }

    public List<PollNode> findAllAndSortByTitle() {
        return findAll().stream()
                .sorted(Comparator.comparing(PollNode::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public PollNode findById(String id) {
        return pollNodeRepository.findById(id).orElseThrow(
                () -> new PollNodeNotFoundException(id));
    }

    @Override
    public void deleteById(String id) {
        if (pollNodeRepository.existsById(id)) {
            pollNodeRepository.deleteById(id);
        } else {
            throw new PollNodeNotFoundException(id);
        }
    }
}
