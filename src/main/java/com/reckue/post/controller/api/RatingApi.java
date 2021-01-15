package com.reckue.post.controller.api;

import com.reckue.post.transfer.PostRatingResponse;
import com.reckue.post.transfer.PostResponse;
import com.reckue.post.transfer.RatingRequest;
import com.reckue.post.transfer.RatingResponse;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Interface RatingApi allows to post annotations for swagger.
 *
 * @author Iveri Narozashvili
 */
@Api(tags = {"/rating"})
public interface RatingApi {
    @ApiOperation(value = "Add a rating", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The rating successfully added"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    RatingResponse create(RatingRequest ratingRequest, HttpServletRequest request);

    @ApiOperation(value = "Update a rating", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The rating successfully updated"),
            @ApiResponse(code = 400, message = "You need to fill in the fields of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to change is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    RatingResponse update(String id, RatingRequest ratingRequest, HttpServletRequest request);

    @ApiOperation(value = "View a list of available ratings", response = RatingResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of ratings successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<RatingResponse> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    @ApiOperation(value = "Delete a rating", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The rating successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteById(String id, HttpServletRequest request);

    @ApiOperation(value = "Count of ratings to one post", response = PostRatingResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The quantity of ratings successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    PostRatingResponse getQuantityOfRatingsToPost(String postId);

    @ApiOperation(value = "View a list of posts with ratings by user", response = PostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of posts successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<PostResponse> findAllPostsByUser(String userId, Integer limit, Integer offset);


}
