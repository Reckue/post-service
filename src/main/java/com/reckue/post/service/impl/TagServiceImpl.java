package com.reckue.post.service.impl;

import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.exception.model.tag.TagNotFoundException;
import com.reckue.post.model.Tag;
import com.reckue.post.processor.annotation.NotNullableArgs;
import com.reckue.post.repository.TagRepository;
import com.reckue.post.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class TagServiceImpl represents realization of TagService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    /**
     * This method is used to create an object of class Tag.
     *
     * @param tag object of class Tag
     * @return tag object of class Tag
     */
    @Override
    @NotNullableArgs
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    /**
     * This method is used to update data in an object of class Tag.
     * Throws {@link TagNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link ReckueIllegalArgumentException} in case
     * if such parameter is null.
     *
     * @param tag object of class Tag
     * @return tag object of class Tag
     */
    @Override
    public Tag update(Tag tag) {
        if (tag.getId() == null) {
            throw new ReckueIllegalArgumentException("The parameter is null");
        }
        Tag savedTag = tagRepository
                .findById(tag.getId())
                .orElseThrow(() -> new TagNotFoundException(tag.getId()));
        savedTag.setName(tag.getName());
        return tagRepository.save(savedTag);
    }

    /**
     * This method is used to get all objects of class Tag.
     *
     * @return list of objects of class Tag
     */
    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    /**
     * This method is used to get all objects of class Tag by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class Tag
     */
    @Override
    public List<Tag> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
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

    /**
     * This method is used to sort objects in descending order by type.
     *
     * @param sort parameter for sorting
     * @param desc sorting descending
     * @return list of objects of class Tag sorted by the selected parameter for sorting
     * in descending order
     */
    public List<Tag> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<Tag> tags = findAllBySortType(sort);
            Collections.reverse(tags);
            return tags;
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: name, default - id
     * @return list of objects of class Tag sorted by the selected parameter for sorting
     */
    public List<Tag> findAllBySortType(String sort) {

        switch (sort) {
            case "name":
                return findAllAndSortByName();
            case "id":
                return findAllAndSortById();
        }
        throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
    }

    /**
     * This method is used to sort objects by id.
     *
     * @return list of objects of class Tag sorted by id
     */
    public List<Tag> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(Tag::getId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by content.
     *
     * @return list of objects of class Tag sorted by content
     */
    public List<Tag> findAllAndSortByName() {
        return findAll().stream()
                .sorted(Comparator.comparing(Tag::getName))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link TagNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return object of class Tag
     */
    @Override
    public Tag findById(String id) {
        return tagRepository.findById(id).orElseThrow(
                () -> new TagNotFoundException(id));
    }

    /**
     * This method is used to delete an object by id.
     * Throws {@link TagNotFoundException} in case
     * if such object isn't contained in database.
     *
     * @param id object
     */
    @Override
    public void deleteById(String id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
        } else {
            throw new TagNotFoundException(id);
        }
    }
}
