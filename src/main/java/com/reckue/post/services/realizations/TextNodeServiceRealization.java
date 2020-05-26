package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.TextNode;
import com.reckue.post.repositories.TextNodeRepository;
import com.reckue.post.services.TextNodeService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Class TextNodeServiceRealization represents realization of TextNodeService.
 *
 * @author Daria Smirnova
 */
@RequiredArgsConstructor
public class TextNodeServiceRealization implements TextNodeService {

    private final TextNodeRepository textNodeRepository;

    /**
     * This method is used to create an object of class TextNode.
     * Throws {@link ModelAlreadyExistsException} in case if such object already exists.
     *
     * @param textNode object of class TextNode
     * @return node object of class TextNode
     */
    @Override
    public TextNode create(TextNode textNode) {
        if (!textNodeRepository.existsById(textNode.getId())) {
            textNode.setId(UUID.randomUUID().toString());
            return textNodeRepository.save(textNode);
        } else {
            throw new ModelAlreadyExistsException("TextNode already exists.");
        }
    }

    /**
     * This method is used to update data in an object of class TextNode.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     *
     * @param textNode object of class TextNode
     * @return textNode object of class TextNode
     */
    @Override
    public TextNode update(TextNode textNode) {
        TextNode savedTextNode;
        if (textNode.getId() != null) {
            savedTextNode = textNodeRepository.findById(textNode.getId()).orElseThrow(
                    () -> new ModelNotFoundException("TextNode not found by id " + textNode.getId() + ".")
            );
            savedTextNode.setContent(textNode.getContent());
            return textNodeRepository.save(savedTextNode);
        } else {
            throw new IllegalArgumentException("TextNode is null");
        }
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
        return textNodeRepository.findAll().stream()
                .limit(limit)
                .skip(offset)
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
                () -> new ModelNotFoundException("TextNode not found by id " + id + "."));
    }

    /**
     * This method is used to delete an object by id.
     *
     * @param id object
     */
    @Override
    public void deleteById(String id) {
        if (textNodeRepository.existsById(id)) {
            textNodeRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("TextNode not found by id " + id + ".");
        }
    }
}