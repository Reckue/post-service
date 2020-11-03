package com.reckue.post.controllers;

import com.reckue.post.controllers.apis.RatingApi;
import com.reckue.post.exceptions.ReckueUnauthorizedException;
import com.reckue.post.models.Rating;
import com.reckue.post.services.RatingService;
import com.reckue.post.services.SecurityService;
import com.reckue.post.transfers.PostRatingResponse;
import com.reckue.post.transfers.PostResponse;
import com.reckue.post.transfers.RatingRequest;
import com.reckue.post.transfers.RatingResponse;
import com.reckue.post.utils.converters.PostConverter;
import com.reckue.post.utils.converters.RatingConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.reckue.post.utils.converters.RatingConverter.convert;

/**
 * Class RatingController is responsible for processing incoming requests.
 *
 * @author Iveri Narozashvili
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rating")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatingController implements RatingApi {

    private final RatingService ratingService;
    private final SecurityService securityService;

    /**
     * This type of request allows to create and process it using the converter.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param ratingRequest the object of class RatingRequest
     * @param request       information for HTTP servlets
     * @return the object of class RatingResponse
     */
    // TODO: add postId as PathVariable and delete it from request (you don't need to enter a postId in update method)
    @PostMapping
    public RatingResponse create(@RequestBody @Valid RatingRequest ratingRequest,
                                 HttpServletRequest request) {
        return convert(ratingService.create(convert(ratingRequest), securityService.checkAndGetInfo(request)));
    }

    /**
     * This type of request allows to update by id the object and process it using the converter.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param id            the object identifier
     * @param ratingRequest the object of class RatingRequest
     * @param request       information for HTTP servlets
     * @return the object of class RatingResponse
     */
    @PutMapping("/{id}")
    public RatingResponse update(@PathVariable String id,
                                 @RequestBody @Valid RatingRequest ratingRequest,
                                 HttpServletRequest request) {
        Rating rating = convert(ratingRequest);
        rating.setId(id);
        return convert(ratingService.update(rating, securityService.checkAndGetInfo(request)));
    }

    /**
     * This type of request allows to find all the objects
     * that meet the requirements, process their using the converter.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of given quantity of objects of class RatingResponse with a given offset
     * sorted by the selected parameter for sorting in descending order
     */
    @GetMapping
    public List<RatingResponse> findAll(@RequestParam(required = false) Integer limit,
                                        @RequestParam(required = false) Integer offset,
                                        @RequestParam(required = false) String sort,
                                        @RequestParam(required = false) Boolean desc) {
        return ratingService.findAll(limit, offset, sort, desc).stream()
                .map(RatingConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * This type of request allows to get all the objects by user id, process it using the converter.
     *
     * @param userId user identifier
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @return list of objects of class RatingResponse
     */
    @GetMapping("/user/{userId}")
    public List<RatingResponse> findAllByUserId(@PathVariable String userId,
                                                @RequestParam(required = false) Integer limit,
                                                @RequestParam(required = false) Integer offset) {
        return ratingService.findAllByUserId(userId, limit, offset).stream()
                .map(RatingConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * This type of request allows to delete the object by id.
     * Throws {@link ReckueUnauthorizedException} in case if token is absent.
     *
     * @param id      the object identifier
     * @param request information for HTTP servlets
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id, HttpServletRequest request) {
        ratingService.deleteById(id, securityService.checkAndGetInfo(request));
    }

    /**
     * This type of request allows to delete the object by id.
     *
     * @param postId the post identifier
     * @return quantity of ratings to one post
     */
    @GetMapping("/post/{postId}")
    public PostRatingResponse getQuantityOfRatingsToPost(@PathVariable String postId) {
        return PostRatingResponse.builder()
                .count(ratingService.getRatingsCountByPostId(postId))
                .build();
    }

    /**
     * This type of request allows to find all posts with rating by the user, process their using the converter.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @return list of given quantity of objects of class RatingResponse with a given offset
     */
    @GetMapping("/post/{userId}")
    public List<PostResponse> findAllPostsByUser(@PathVariable String userId,
                                                 @RequestParam(required = false) Integer limit,
                                                 @RequestParam(required = false) Integer offset) {
        return ratingService.findAllPostsWithRatingsByUserId(userId, limit, offset).stream()
                .map(PostConverter::convert)
                .collect(Collectors.toList());
    }
}
