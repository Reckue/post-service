package com.reckue.post.controllers.apis;

import com.reckue.post.transfers.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Interface TagApiController allows to post annotations for swagger.
 *
 * @author Kamila Meshcheryakova
 */
@Api(tags = {"/tags"})
public interface TagApiController {

    @ApiOperation(value = "Add a tag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The tag successfully added"),
            @ApiResponse(code = 409, message = "The tag you want to add already exists"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    @PostMapping
    TagResponse create(@RequestBody @Valid TagRequest tagRequest);

    @ApiOperation(value = "Update a tag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The tag successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    @PutMapping("/{id}")
    TagResponse update(@PathVariable String id, @RequestBody @Valid TagRequest tagRequest);

    @ApiOperation(value = "View a list of available tags", response = TagResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of tags successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    @GetMapping
    List<TagResponse> findAll(@RequestParam int limit, @RequestParam int offset,
                              @RequestParam String sort, @RequestParam boolean desc);

    @ApiOperation(value = "Search a tag with an ID", response = TagResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The tag successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    @GetMapping("/{id}")
    TagResponse findById(@PathVariable String id);

    @ApiOperation(value = "Delete a tag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The tag successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")
    }
    )
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable String id);
}
