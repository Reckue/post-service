package com.reckue.post.controller;

import com.reckue.post.controller.api.CommentApi;
import com.reckue.post.exception.ReckueUnauthorizedException;
import com.reckue.post.model.Comment;
import com.reckue.post.service.CommentService;
import com.reckue.post.service.SecurityService;
import com.reckue.post.transfer.dto.CommentRequest;
import com.reckue.post.transfer.dto.CommentResponse;
import com.reckue.post.util.converter.CommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.reckue.post.util.converter.CommentConverter.convert;

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
    private final SecurityService securityService;

    /**
     * This type of request allows to create and process it using the converter.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param commentRequest the object of class CommentRequest
     * @param request        information for HTTP servlets
     * @return the object of class CommentResponse
     */
    @PostMapping
    // TODO: add postId as PathVariable and delete it from request (you don't need to enter a postId in update method)
    public CommentResponse create(@RequestBody @Valid CommentRequest commentRequest,
                                  HttpServletRequest request) {
        return convert(commentService.create(convert(commentRequest), securityService.checkAndGetInfo(request)));
    }

    /**
     * This type of request allows to update by id the object and process it using the converter.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param id             the object identifier
     * @param commentRequest the object of class CommentRequest
     * @param request        information for HTTP servlets
     * @return the object of class CommentResponse
     */
    @PutMapping("/{id}")
    public CommentResponse update(@PathVariable String id,
                                  @RequestBody @Valid CommentRequest commentRequest,
                                  HttpServletRequest request) {
        Comment comment = convert(commentRequest);
        comment.setId(id);
        return convert(commentService.update(comment, securityService.checkAndGetInfo(request)));
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
     * This type of request allows to get all the objects by user id, process it using the converter.
     *
     * @param userId user identifier
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @return list of objects of class CommentResponse
     */
    @GetMapping("/user/{userId}")
    public List<CommentResponse> findAllByUserId(@PathVariable String userId,
                                                 @RequestParam(required = false) Integer limit,
                                                 @RequestParam(required = false) Integer offset) {
        return commentService.findAllByUserId(userId, limit, offset).stream()
                .map(CommentConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * This type of request allows to delete the object by id.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param id      the object identifier
     * @param request information for HTTP servlets
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id, HttpServletRequest request) {
        commentService.deleteById(id, securityService.checkAndGetInfo(request));
    }
}
