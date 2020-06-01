package com.reckue.post.controllers;

import com.reckue.post.converters.PostConverter;
import com.reckue.post.models.Post;
import com.reckue.post.services.PostService;
import com.reckue.post.transfers.PostRequest;
import com.reckue.post.transfers.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.reckue.post.converters.PostConverter.convert;

/**
 * Class PostController represents simple REST-Controller.
 *
 * @author Kamila Meshcheryakova
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {

    private final PostService postService;

    /**
     * This type of request allows to create, process it using the converter and save.
     *
     * @param postRequest the object of class PostRequest
     * @return the object of class PostResponse
     */
    @PostMapping
    public PostResponse save(@RequestBody @Valid PostRequest postRequest) {
        return convert(postService.create(convert(postRequest)));
    }

    /**
     * This type of request allows to update by id the object, process it using the converter and save.
     *
     * @param id          the object identifier
     * @param postRequest the object of class PostRequest
     * @return the object of class PostResponse
     */
    @PutMapping("/{id}")
    public PostResponse update(@PathVariable String id, @RequestBody @Valid PostRequest postRequest) {
        Post post = convert(postRequest);
        post.setId(id);
        return convert(postService.update(post));
    }

    /**
     * This type of request allows to get the object by id, process it using the converter.
     *
     * @param id the object identifier
     * @return the object of class PostResponse
     */
    @GetMapping("/{id}")
    public PostResponse getById(@PathVariable String id) {
        return convert(postService.findById(id));
    }

    /**
     * This type of request allows to get all the objects that meet the requirements, process it using the converter.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of given quantity of objects of class PostResponse with a given offset
     * sorted by the selected parameter for sorting in descending order
     */
    @GetMapping
    public List<PostResponse> getAll(@RequestParam int limit, @RequestParam int offset,
                                     @RequestParam String sort, @RequestParam boolean desc) {

        return postService.findAll(limit, offset, sort, desc).stream()
                .map(PostConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * This type of request allows to delete the object by id.
     *
     * @param id the object identifier
     */
    @DeleteMapping("{/id}")
    public void deleteById(@PathVariable String id) {
        postService.deleteById(id);
    }

}
