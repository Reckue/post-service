package com.reckue.post.controller;

import com.reckue.post.controller.api.TagApi;
import com.reckue.post.generated.models.TagRequest;
import com.reckue.post.generated.models.TagResponse;
import com.reckue.post.model.Tag;
import com.reckue.post.service.TagService;
import com.reckue.post.util.converter.TagConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.reckue.post.util.converter.TagConverter.convert;

/**
 * Class TagController is responsible for processing incoming requests.
 *
 * @author Daria Smirnova
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tags")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagController implements TagApi {

    private final TagService tagService;

    /**
     * This type of request allows to create and process it using the converter.
     *
     * @param tagRequest the object of class TagRequest
     * @return the object of class TagResponse
     */
    @PostMapping
    public TagResponse create(@RequestBody @Valid TagRequest tagRequest) {
        return convert(tagService.create(convert(tagRequest)));
    }

    /**
     * This type of request allows to update by id the object and process it using the converter.
     *
     * @param id         the object identifier
     * @param tagRequest the object of class TagRequest
     * @return the object of class TagResponse
     */
    @PutMapping("/{id}")
    public TagResponse update(@PathVariable String id, @RequestBody @Valid TagRequest tagRequest) {
        Tag tag = convert(tagRequest);
        tag.setId(id);
        return convert(tagService.update(tag));
    }

    /**
     * This type of request allows to find all the objects
     * that meet the requirements, process their using the converter.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of given quantity of objects of class TagResponse with a given offset
     * sorted by the selected parameter for sorting in descending order
     */
    @GetMapping
    public List<TagResponse> findAll(@RequestParam(required = false) Integer limit,
                                     @RequestParam(required = false) Integer offset,
                                     @RequestParam(required = false) String sort,
                                     @RequestParam(required = false) Boolean desc) {

        return tagService.findAll(limit, offset, sort, desc).stream()
                .map(TagConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * This type of request allows to get the object by id, process it using the converter.
     *
     * @param id the object identifier
     * @return the object of class TagResponse
     */
    @GetMapping("/{id}")
    public TagResponse findById(@PathVariable String id) {
        return convert(tagService.findById(id));
    }

    /**
     * This type of request allows to delete the object by id.
     *
     * @param id the object identifier
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        tagService.deleteById(id);
    }
}
