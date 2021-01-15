package com.reckue.post.service.impl;

import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.exception.model.tag.TagNotFoundException;
import com.reckue.post.model.Tag;
import com.reckue.post.processor.notnull.NotNullArgs;
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

    @Override
    @NotNullArgs
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

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

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

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

    public List<Tag> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<Tag> tags = findAllBySortType(sort);
            Collections.reverse(tags);
            return tags;
        }
        return findAllBySortType(sort);
    }

    public List<Tag> findAllBySortType(String sort) {
        switch (sort) {
            case "name":
                return findAllAndSortByName();
            case "id":
                return findAllAndSortById();
            default:
                throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
        }
    }

    public List<Tag> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(Tag::getId))
                .collect(Collectors.toList());
    }

    public List<Tag> findAllAndSortByName() {
        return findAll().stream()
                .sorted(Comparator.comparing(Tag::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Tag findById(String id) {
        return tagRepository.findById(id).orElseThrow(
                () -> new TagNotFoundException(id));
    }

    @Override
    public void deleteById(String id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
        } else {
            throw new TagNotFoundException(id);
        }
    }
}
