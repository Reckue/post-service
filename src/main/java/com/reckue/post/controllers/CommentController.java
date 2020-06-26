package com.reckue.post.controllers;

import com.reckue.post.controllers.apis.CommentApi;
import com.reckue.post.models.Comment;
import com.reckue.post.services.CommentService;
import com.reckue.post.transfers.CommentRequest;
import com.reckue.post.transfers.CommentResponse;
import com.reckue.post.utils.converters.CommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.reckue.post.utils.converters.CommentConverter.convert;

/**
 * Class CommentController is responsible for processing incoming requests.
 *
 * @author Artur Magomedov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController implements CommentApi {

    private final CommentService commentService;

    /**
     * This type of request allows to create and process it using the converter.
     *
     * @param commentRequest the object of class CommentRequest
     * @return the object of class CommentResponse
     */
    @PostMapping
    public CommentResponse create(@RequestBody @Valid CommentRequest commentRequest) {
        return convert(commentService.create(convert(commentRequest)));
    }

    /**
     * This type of request allows to update by id the object and process it using the converter.
     *
     * @param id             the object identifier
     * @param commentRequest the object of class CommentRequest
     * @return the object of class CommentResponse
     */
    @PutMapping("/{id}")
    public CommentResponse update(@PathVariable String id, @RequestBody @Valid CommentRequest commentRequest) {
        Comment comment = convert(commentRequest);
        comment.setId(id);
        return convert(commentService.update(comment));
    }

    /**
     * This type of request allows to find all the objects
     * that meet the requirements, process their using the converter.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of given quantity of objects of class CommentResponse with a given offset
     * sorted by the selected parameter for sorting in descending order
     */
    @GetMapping
    public List<CommentResponse> findAll(@RequestParam(required = false) Integer limit,
                                         @RequestParam(required = false) Integer offset,
                                         @RequestParam(required = false) String sort,
                                         @RequestParam(required = false) Boolean desc) {

        return commentService.findAll(limit, offset, sort, desc).stream()
                .map(CommentConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * This type of request allows to get the object by id, process it using the converter.
     *
     * @param id the object identifier
     * @return the object of class CommentResponse
     */
    @GetMapping("/{id}")
    public CommentResponse findById(@PathVariable String id) {
        return convert(commentService.findById(id));
    }

    /**
     * This type of request allows to delete the object by id.
     *
     * @param id the object identifier
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        commentService.deleteById(id);
    }
}
