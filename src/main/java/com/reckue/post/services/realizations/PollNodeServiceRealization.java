package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.nodes.PollNode;
import com.reckue.post.repositories.PollNodeRepository;
import com.reckue.post.services.PollNodeService;
import com.reckue.post.utils.Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class PollNodeServiceRealization represents realization of PollNodeService.
 *
 * @author Grigoriev Viktor
 */
@Service
@RequiredArgsConstructor
public class PollNodeServiceRealization implements PollNodeService {

    private final PollNodeRepository pollNodeRepository;

    /**
     * This method is used to create an object of class PollNode.
     * Throws {@link ModelAlreadyExistsException} in case if such object already exists.
     *
     * @param node object of class PollNode
     * @return node object of class PollNode
     */
    @Override
    public PollNode create(PollNode node) {
        node.setId(Generator.id());
        if (!pollNodeRepository.existsById(node.getId())) {
            return pollNodeRepository.save(node);
        } else {
            throw new ModelAlreadyExistsException("PollNode already exists");
        }
    }

    /**
     * This method is used to update data in an object of class PollNode.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link IllegalArgumentException} in case
     * if such parameter is null.
     *
     * @param node object of class PollNode
     * @return node object of class PollNode
     */
    @Override
    public PollNode update(PollNode node) {
        if (node.getId() == null || !pollNodeRepository.existsById(node.getId())) {
            throw new ModelNotFoundException("PollNode by id " + node.getId() + " is not found");
        }
        PollNode savedPollNode = PollNode.builder()
                .id(node.getId())
                .title(node.getTitle())
                .items(node.getItems())
                .build();
        return pollNodeRepository.save(savedPollNode);
    }

    /**
     * This method is used to get all objects of class PollNode.
     *
     * @return list of objects of class PollNode
     */
    @Override
    public List<PollNode> findAll() {
        return pollNodeRepository.findAll();
    }

    /**
     * This method is used to get all objects of class PollNode by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class PollNode
     */
    @Override
    public List<PollNode> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
        if (limit == null) limit = 10;
        if (offset == null) offset = 0;
        if (StringUtils.isEmpty(sort)) sort = "id";
        if (desc == null) desc = false;

        if (limit < 0 || offset < 0) {
            throw new IllegalArgumentException("Limit or offset is incorrect");
        }
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
     * @return list of objects of class PollNode sorted by the selected parameter for sorting
     * in descending order
     */
    public List<PollNode> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<PollNode> node = findAllBySortType(sort);
            Collections.reverse(node);
            return node;
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: title, default - id
     * @return list of objects of class PollNode sorted by the selected parameter for sorting
     */
    public List<PollNode> findAllBySortType(String sort) {

        switch (sort) {
            case "title":
                return findAllAndSortByTitle();
            case "id":
                return findAllAndSortById();
        }
        throw new IllegalArgumentException("Such field as " + sort + " doesn't exist");
    }

    /**
     * This method is used to sort objects by id.
     *
     * @return list of objects of class PollNode sorted by id
     */
    public List<PollNode> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(PollNode::getId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by title.
     *
     * @return list of objects of class PollNode sorted by title
     */
    public List<PollNode> findAllAndSortByTitle() {
        return findAll().stream()
                .sorted(Comparator.comparing(PollNode::getTitle))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return object of class PollNode
     */
    @Override
    public PollNode findById(String id) {
        return pollNodeRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException("PollNode by id " + id + " is not found"));
    }

    /**
     * This method is used to delete an object by id.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     *
     * @param id object
     */
    @Override
    public void deleteById(String id) {
        if (pollNodeRepository.existsById(id)) {
            pollNodeRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("PollNode by id " + id + " is not found");
        }
    }
}
