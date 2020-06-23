package com.reckue.post.controllers.apis;

import com.reckue.post.transfers.RatingRequest;
import com.reckue.post.transfers.RatingResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**
 * Interface RatingApi allows to post annotations for swagger.
 *
 * @author Iveri Narozashvili
 */
@Api(tags = {"/tags"})
public interface RatingApi {
    @ApiOperation(value = "Add a rating")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The rating successfully added"),
            @ApiResponse(code = 409, message = "The rating you want to add already exists"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    RatingResponse create(RatingRequest ratingRequest);

    @ApiOperation(value = "Update a rating")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The rating successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    RatingResponse update(String id, RatingRequest ratingRequest);

    @ApiOperation(value = "View a list of available ratings", response = RatingResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of tags successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<RatingResponse> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    @ApiOperation(value = "Get a tag by id", response = RatingResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The rating successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    RatingResponse findById(String id);

    @ApiOperation(value = "Delete a tag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The rating successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteById(String id);
}
