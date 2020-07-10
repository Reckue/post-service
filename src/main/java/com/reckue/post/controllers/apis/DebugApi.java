package com.reckue.post.controllers.apis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Interface DataBaseUtilApi allows to post annotations for swagger.
 *
 * @author Marina Buinevich
 */
@Api(tags = {"/debug"})
public interface DebugApi {

    @Deprecated
    @ApiOperation(value = "Delete all nodes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The nodes successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteAllNodes();

    @Deprecated
    @ApiOperation(value = "Delete all posts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The posts successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteAllPosts();

    @Deprecated
    @ApiOperation(value = "Delete all ratings")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The ratings successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteAllRatings();
}
