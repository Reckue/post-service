package com.reckue.post.controllers.apis;

import com.reckue.post.transfers.PostRequest;
import com.reckue.post.transfers.PostResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**
 * Interface PostApi allows to post annotations for swagger.
 *
 * @author Kamila Meshcheryakova
 */
@Api(tags = {"/posts"})
@SuppressWarnings("unused")
public interface PostApi {

    @ApiOperation(value = "Add a post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully added"),
            @ApiResponse(code = 409, message = "The post you want to add already exists"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    PostResponse save(PostRequest postRequest);

    @ApiOperation(value = "Update a post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    PostResponse update(String id, PostRequest postRequest);

    @ApiOperation(value = "Search a post with an ID", response = PostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    PostResponse findById(String id);

    @ApiOperation(value = "View a list of available posts", response = PostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of posts successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<PostResponse> findAll(Integer limit, Integer offset, String sort, boolean desc);

    @ApiOperation(value = "Delete a post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteById(String id);
}
