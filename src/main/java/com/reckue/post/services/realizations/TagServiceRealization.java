package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.models.tag.TagAlreadyExistException;
import com.reckue.post.exceptions.models.tag.TagNotFoundException;
import com.reckue.post.models.Tag;
import com.reckue.post.repositories.TagRepository;
import com.reckue.post.services.TagService;
import com.reckue.post.utils.Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class TagServiceRealization represents realization of TagService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class TagServiceRealization implements TagService {

    private final TagRepository tagRepository;

    /**
     * This method is used to create an object of class Tag.
     * Throws {@link TagAlreadyExistException} in case if such object already exists.
     *
     * @param tag object of class Tag
     * @return tag object of class Tag
     */
    @Override
    public Tag create(Tag tag) {
        tag.setId(Generator.id());
        if (!tagRepository.existsById(tag.getId())) {
            return tagRepository.save(tag);
        } else {
            throw new TagAlreadyExistException(tag.getId());
        }
    }

    /**
     * This method is used to update data in an object of class Tag.
     * Throws {@link TagNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link IllegalArgumentException} in case
     * if such parameter is null.
     *
     * @param tag object of class Tag
     * @return tag object of class Tag
     */
    @Override
    public Tag update(Tag tag) {
        if (tag.getId() == null) {
            throw new IllegalArgumentException("The parameter is null");
        }
        if (!tagRepository.existsById(tag.getId())) {
            throw new TagNotFoundException(tag.getId());
        }
        Tag savedTag = Tag.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
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
        throw new IllegalArgumentException("Such field as " + sort + " doesn't exist");
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
