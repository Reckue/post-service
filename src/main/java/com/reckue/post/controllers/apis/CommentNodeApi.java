package com.reckue.post.controllers.apis;

import com.reckue.post.transfers.CommentNodeRequest;
import com.reckue.post.transfers.CommentNodeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**
 * Interface CommentNodeApi allows to post annotations for swagger.
 *
 * @author Artur Magomedov
 */
@Api(tags = {"/comment_nodes"})
public interface CommentNodeApi {

    @ApiOperation(value = "Add a comment node")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The comment node successfully added"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    CommentNodeResponse create(CommentNodeRequest nodeRequest);

    @ApiOperation(value = "Update a node")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The comment node successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    CommentNodeResponse update(String id, CommentNodeRequest nodeRequest);

    @ApiOperation(value = "View a list of available comment nodes", response = CommentNodeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of comment nodes successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<CommentNodeResponse> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    @ApiOperation(value = "Get a comment node by id", response = CommentNodeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The comment node successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    CommentNodeResponse findById(String id);

    @ApiOperation(value = "Delete a comment node")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The comment node successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteById(String id);
}
