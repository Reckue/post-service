package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.TextNode;
import com.reckue.post.repositories.TextNodeRepository;
import com.reckue.post.services.TextNodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Class TextNodeServiceRealization represents realization of TextNodeService.
 *
 * @author Daria Smirnova
 */
@Service
@RequiredArgsConstructor
public class TextNodeServiceRealization implements TextNodeService {

    private final TextNodeRepository textNodeRepository;

    /**
     * This method is used to create an object of class TextNode.
     * Throws {@link ModelAlreadyExistsException} in case if such object already exists.
     *
     * @param textNode object of class TextNode
     * @return textNode object of class TextNode
     */
    @Override
    public TextNode create(TextNode textNode) {
        if (!textNodeRepository.existsById(textNode.getId())) {
            textNode.setId(UUID.randomUUID().toString());
            return textNodeRepository.save(textNode);
        } else {
            throw new ModelAlreadyExistsException("Text node already exists");
        }
    }

    /**
     * This method is used to update data in an object of class TextNode.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link IllegalArgumentException} in case
     * if such parameter is null.
     *
     * @param textNode object of class TextNode
     * @return textNode object of class TextNode
     */
    @Override
    public TextNode update(TextNode textNode) {
        if (textNode.getId() == null) {
            throw new IllegalArgumentException("The parameter is null");
        }
        TextNode savedTextNode = textNodeRepository.findById(textNode.getId()).orElseThrow(
                () -> new ModelNotFoundException("Text node by id " + textNode.getId() + " is not found")
        );
        savedTextNode.setContent(textNode.getContent());
        return textNodeRepository.save(savedTextNode);
    }

    /**
     * This method is used to get all objects of class TextNode.
     *
     * @return list of objects of class TextNode
     */
    @Override
    public List<TextNode> findAll() {
        return textNodeRepository.findAll();
    }

    /**
     * This method is used to get all objects of class TextNode by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class TextNode
     */
    @Override
    public List<TextNode> findAll(int limit, int offset, String sort, boolean desc) {
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
     * @return list of objects of class TextNode sorted by the selected parameter for sorting
     * in descending order
     */
    public List<TextNode> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<TextNode> nodes = findAllBySortType(sort);
            Collections.reverse(nodes);
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: content, default - id
     * @return list of objects of class TextNode sorted by the selected parameter for sorting
     */
    public List<TextNode> findAllBySortType(String sort) {
        if (sort.equals("content")) {
            return findAllAndSortByContent();
        }
        return findAllAndSortById();
    }

    /**
     * This method is used to sort objects by id.
     *
     * @return list of objects of class TextNode sorted by id
     */
    public List<TextNode> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(TextNode::getId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by content.
     *
     * @return list of objects of class TextNode sorted by content
     */
    public List<TextNode> findAllAndSortByContent() {
        return findAll().stream()
                .sorted(Comparator.comparing(TextNode::getContent))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return object of class TextNode
     */
    @Override
    public TextNode findById(String id) {
        return textNodeRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException("Text node by id " + id + " is not found"));
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
        if (textNodeRepository.existsById(id)) {
            textNodeRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("Text node by id " + id + " is not found");
        }
    }
}
