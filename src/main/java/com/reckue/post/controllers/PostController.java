package com.reckue.post.controllers;

import com.reckue.post.utils.converters.PostConverter;
import com.reckue.post.models.Post;
import com.reckue.post.services.PostService;
import com.reckue.post.transfers.PostRequest;
import com.reckue.post.transfers.PostResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.reckue.post.utils.converters.PostConverter.convert;

/**
 * Class PostController represents simple REST-Controller.
 *
 * @author Kamila Meshcheryakova
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
@Api (tags = "Post controller", value = "post controller", description = "Operations pertaining to posts")
//@SwaggerDefinition(tags = { @Tag(name = "Post controller", description = "Operations pertaining to posts") })
public class PostController {

    private final PostService postService;

    /**
     * This type of request allows to create, process it using the converter and save.
     *
     * @param postRequest the object of class PostRequest
     * @return the object of class PostResponse
     */
    @ApiOperation(value = "Add a post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully added"),
            @ApiResponse(code = 409, message = "The post you want to add already exists"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
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
    @ApiOperation(value = "Update a post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
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
    @ApiOperation(value = "Search a post with an ID",response = PostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    @GetMapping("/{id}")
    public PostResponse findById(@PathVariable String id) {
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
    @ApiOperation(value = "View a list of available posts",response = PostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of posts successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    @GetMapping
    public List<PostResponse> findAll(@RequestParam int limit, @RequestParam int offset,
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
    @ApiOperation(value = "Delete a post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        postService.deleteById(id);
    }
}
