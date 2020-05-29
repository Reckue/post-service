package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.CodeNode;
import com.reckue.post.repositories.CodeNodeRepository;
import com.reckue.post.services.CodeNodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Class CodeNodeServiceRealization represents realization of CodeNodeService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class CodeNodeServiceRealization implements CodeNodeService {

    private final CodeNodeRepository codeNodeRepository;

    /**
     * This method is used to create an object of class CodeNode.
     * Throws {@link ModelAlreadyExistsException} in case if such object already exists.
     *
     * @param codeNode object of class CodeNode
     * @return node object of class CodeNode
     */
    @Override
    public CodeNode create(CodeNode codeNode) {
        if (!codeNodeRepository.existsById(codeNode.getId())) {
            codeNode.setId(UUID.randomUUID().toString());
            return codeNodeRepository.save(codeNode);
        } else {
            throw new ModelAlreadyExistsException("CodeNode already exists.");
        }
    }

    /**
     * This method is used to update data in an object of class CodeNode.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link IllegalArgumentException} in case
     * if such parameter is null.
     *
     * @param codeNode object of class CodeNode
     * @return codeNode object of class CodeNode
     */
    @Override
    public CodeNode update(CodeNode codeNode) {
        if (codeNode.getId() == null) {
            throw new IllegalArgumentException("The parameter is null.");
        }
        if (!codeNodeRepository.existsById(codeNode.getId())) {
            throw new ModelNotFoundException("Post not found by id " + codeNode.getId() + ".");
        }
        CodeNode savedCodeNode = CodeNode.builder()
                .language(codeNode.getLanguage())
                .content(codeNode.getContent())
                .build();
        return codeNodeRepository.save(savedCodeNode);
    }

    /**
     * This method is used to get all objects of class CodeNode.
     *
     * @return list of objects of class CodeNode
     */
    @Override
    public List<CodeNode> findAll() {
        return codeNodeRepository.findAll();
    }

    /**
     * This method is used to get all objects of class CodeNode by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class CodeNode
     */
    @Override
    public List<CodeNode> findAll(int limit, int offset, String sort, boolean desc) {
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
     * @return list of objects of class CodeNode sorted by the selected parameter for sorting
     * in descending order
     */
    public List<CodeNode> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<CodeNode> nodes = findAllBySortType(sort);
            Collections.reverse(nodes);
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: language, content, default - id
     * @return list of objects of class CodeNode sorted by the selected parameter for sorting
     */
    public List<CodeNode> findAllBySortType(String sort) {
        switch (sort) {
            case "title":
                return findAllAndSortByLanguage();
            case "source":
                return findAllAndSortByContent();
        }
        return findAllAndSortById();
    }

    /**
     * This method is used to sort objects by id.
     *
     * @return list of objects of class CodeNode sorted by id
     */
    public List<CodeNode> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(CodeNode::getId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by language.
     *
     * @return list of objects of class CodeNode sorted by language
     */
    public List<CodeNode> findAllAndSortByLanguage() {
        return findAll().stream()
                .sorted(Comparator.comparing(CodeNode::getLanguage))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by content.
     *
     * @return list of objects of class CodeNode sorted by content
     */
    public List<CodeNode> findAllAndSortByContent() {
        return findAll().stream()
                .sorted(Comparator.comparing(CodeNode::getContent))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return object of class CodeNode
     */
    @Override
    public CodeNode findById(String id) {
        return codeNodeRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException("CodeNode not found by id " + id + "."));
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
        if (codeNodeRepository.existsById(id)) {
            codeNodeRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("CodeNode not found by id " + id + ".");
        }
    }
}
