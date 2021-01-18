package com.reckue.post.controller.api;

import com.reckue.post.transfer.NodeRequest;
import com.reckue.post.transfer.NodeResponse;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Interface NodeApi allows to post annotations for swagger.
 *
 * @author Kamila Meshcheryakova
 */
@Api(tags = {"/nodes"})
public interface NodeApi {

    @ApiOperation(value = "Add a node", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The node successfully added"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    NodeResponse create(NodeRequest nodeRequest);

    @ApiOperation(value = "Update a node", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The node successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    NodeResponse update(String id, NodeRequest nodeRequest);

    @ApiOperation(value = "View a list of available nodes", response = NodeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of nodes successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<NodeResponse> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    @ApiOperation(value = "Get a node by id", response = NodeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The node successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    NodeResponse findById(String id);

    @ApiOperation(value = "Delete a node", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The node successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteById(String id);
}
