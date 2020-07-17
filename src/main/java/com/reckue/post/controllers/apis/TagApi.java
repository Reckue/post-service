package com.reckue.post.controllers.apis;

import com.reckue.post.transfers.TagRequest;
import com.reckue.post.transfers.TagResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**
 * Interface TagApi allows to post annotations for swagger.
 *
 * @author Kamila Meshcheryakova
 */
@Api(tags = {"/tags"})
public interface TagApi {

    @ApiOperation(value = "Add a tag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The tag successfully added"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    TagResponse create(TagRequest tagRequest);

    @ApiOperation(value = "Update a tag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The tag successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    TagResponse update(String id, TagRequest tagRequest);

    @ApiOperation(value = "View a list of available tags", response = TagResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of tags successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<TagResponse> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    @ApiOperation(value = "Get a tag by id", response = TagResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The tag successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    TagResponse findById(String id);

    @ApiOperation(value = "Delete a tag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The tag successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteById(String id);
}
