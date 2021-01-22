package com.reckue.post.controller.api;

import com.reckue.post.transfer.dto.post.PostRequest;
import com.reckue.post.transfer.dto.post.PostResponse;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Interface PostApi allows to post annotations for swagger.
 *
 * @author Kamila Meshcheryakova
 */
@Api(tags = {"/posts"})
public interface PostApi {

    @ApiOperation(value = "Add a post", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully added"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    PostResponse create(PostRequest postRequest, HttpServletRequest request);

    @ApiOperation(value = "Update a post", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    PostResponse update(String id, PostRequest postRequest, HttpServletRequest request);

    @ApiOperation(value = "Get a post by id", response = PostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    PostResponse findById(String id);

    @SuppressWarnings("unused")
    @ApiOperation(value = "Get the posts by title", response = PostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The posts successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<PostResponse> findByTitle(String title);

    @ApiOperation(value = "View a list of available posts", response = PostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of posts successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<PostResponse> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    @ApiOperation(value = "Get the posts by user id", response = PostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The posts successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<PostResponse> findAllByUserId(String userId, Integer limit, Integer offset);

    @ApiOperation(value = "Delete a post", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The post successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteById(String id, HttpServletRequest request);
}
