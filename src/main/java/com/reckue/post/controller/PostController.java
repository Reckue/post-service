package com.reckue.post.controller;

import com.reckue.post.controller.api.PostApi;
import com.reckue.post.exception.ReckueUnauthorizedException;
import com.reckue.post.model.Post;
import com.reckue.post.service.PostService;
import com.reckue.post.service.proxy.ProxyService;
import com.reckue.post.transfer.PostRequest;
import com.reckue.post.transfer.PostResponse;
import com.reckue.post.util.converter.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.reckue.post.util.converter.PostConverter.convert;

/**
 * Class PostController represents simple REST-Controller.
 *
 * @author Kamila Meshcheryakova
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController implements PostApi {

    private final PostService postService;
    private final ProxyService<Post> postProxyService;

    /**
     * This type of request allows to create, process it using the converter and save.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param postRequest the object of class PostRequest
     * @param request     information for HTTP servlets
     * @return the object of class PostResponse
     */
    @PostMapping
    public PostResponse create(@RequestBody @Valid PostRequest postRequest, HttpServletRequest request) {
        return convert(postProxyService.create(convert(postRequest), request));
    }

    /**
     * This type of request allows to update by id the object, process it using the converter and save.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param id          the object identifier
     * @param postRequest the object of class PostRequest
     * @param request     information for HTTP servlets
     * @return the object of class PostResponse
     */
    @PutMapping("/{id}")
    public PostResponse update(@PathVariable String id,
                               @RequestBody @Valid PostRequest postRequest,
                               HttpServletRequest request) {
        Post post = convert(postRequest);
        post.setId(id);
        return convert(postProxyService.update(post, request));
    }

    /**
     * This type of request allows to get the object by id, process it using the converter.
     *
     * @param id the object identifier
     * @return the object of class PostResponse
     */
    @GetMapping("/{id}")
    public PostResponse findById(@PathVariable String id) {
        return convert(postService.findById(id));
    }

    /**
     * This type of request allows to get all the objects by title, process it using the converter.
     *
     * @param title the object identifier
     * @return list of objects of class PostResponse
     */
    @GetMapping("/title/{title}")
    public List<PostResponse> findByTitle(@PathVariable String title) {
        return postService.findAllByTitle(title).stream()
                .map(PostConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * This type of request allows to get all the objects by user id, process it using the converter.
     *
     * @param userId user identifier
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @return list of objects of class PostResponse
     */
    @GetMapping("/user/{userId}")
    public List<PostResponse> findAllByUserId(@PathVariable String userId,
                                              @RequestParam(required = false) Integer limit,
                                              @RequestParam(required = false) Integer offset) {
        return postService.findAllByUserId(userId, limit, offset).stream()
                .map(PostConverter::convert)
                .collect(Collectors.toList());
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
    public List<PostResponse> findAll(@RequestParam(required = false) Integer limit,
                                      @RequestParam(required = false) Integer offset,
                                      @RequestParam(required = false) String sort,
                                      @RequestParam(required = false) Boolean desc) {

        return postService.findAll(limit, offset, sort, desc).stream()
                .map(PostConverter::convert)
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
        postProxyService.deleteById(id, request);
    }
}
