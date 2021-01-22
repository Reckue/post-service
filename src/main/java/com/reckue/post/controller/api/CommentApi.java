package com.reckue.post.controller.api;

import com.reckue.post.transfer.dto.CommentRequest;
import com.reckue.post.transfer.dto.CommentResponse;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Interface CommentApi allows to post annotations for swagger.
 *
 * @author Artur Magomedov
 */
@Api(tags = {"/comments"})
public interface CommentApi {

    @ApiOperation(value = "Add a comment", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The comment successfully added"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    CommentResponse create(CommentRequest commentRequest, HttpServletRequest request);

    @ApiOperation(value = "Update a comment", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The comment successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    CommentResponse update(String id, CommentRequest commentRequest, HttpServletRequest request);

    @ApiOperation(value = "View a list of available comments", response = CommentResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of comments successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<CommentResponse> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    @ApiOperation(value = "Get a comment by id", response = CommentResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The comment successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    CommentResponse findById(String id);

    @ApiOperation(value = "Get a list of comments by user id", response = CommentResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of comments successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<CommentResponse> findAllByUserId(String userId, Integer limit, Integer offset);

    @ApiOperation(value = "Delete a comment", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The comment successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteById(String id, HttpServletRequest request);
}
