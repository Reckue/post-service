package com.reckue.post.controller;

import com.reckue.post.generated.controller.TagsApi;
import com.reckue.post.generated.controller.dto.TagRequestDto;
import com.reckue.post.generated.controller.dto.TagResponseDto;
import com.reckue.post.model.Tag;
import com.reckue.post.service.TagService;
import com.reckue.post.util.converter.TagConverter;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Class TagController is responsible for processing incoming requests.
 *
 * @author Daria Smirnova
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagController implements TagsApi {

    private final TagService tagService;

    @Override
    public ResponseEntity<TagResponseDto> createTag(@Valid TagRequestDto tagRequestDto) {
        Tag tag = tagService.create(TagConverter.convertToModel(tagRequestDto));
        return new ResponseEntity<>(TagConverter.convertToDto(tag), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TagResponseDto> updateTag(@Valid TagRequestDto tagRequestDto) {
        Tag tag = tagService.update(TagConverter.convertToModel(tagRequestDto));
        return new ResponseEntity<>(TagConverter.convertToDto(tag), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TagResponseDto>> getAllTags(@Valid Integer limit, @Valid Integer offset,
                                                     @Valid String sort, @Valid Boolean desc) {
        List<TagResponseDto> tagsList = TagConverter.convertToDtoList(tagService.findAll(limit, offset, sort, desc));
        return new ResponseEntity<>(tagsList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TagResponseDto> getTagById(String id) {
        return new ResponseEntity<>(TagConverter.convertToDto(tagService.findById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTagById(String id) {
        tagService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
