package com.reckue.post.controllers;

import com.reckue.post.controllers.apis.RatingApi;
import com.reckue.post.models.Rating;
import com.reckue.post.services.realizations.RatingServiceRealization;
import com.reckue.post.transfers.PostRatingResponse;
import com.reckue.post.transfers.PostResponse;
import com.reckue.post.transfers.RatingRequest;
import com.reckue.post.transfers.RatingResponse;
import com.reckue.post.utils.converters.PostConverter;
import com.reckue.post.utils.converters.RatingConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    private final RatingServiceRealization ratingService;

    /**
     * This type of request allows to create and process it using the converter.
     *
     * @param ratingRequest the object of class RatingRequest
     * @return the object of class RatingResponse
     */
    @PostMapping
    public RatingResponse create(@RequestBody @Valid RatingRequest ratingRequest) {
        return convert(ratingService.create(convert(ratingRequest)));
    }

    /**
     * This type of request allows to update by id the object and process it using the converter.
     *
     * @param id            the object identifier
     * @param ratingRequest the object of class RatingRequest
     * @return the object of class RatingResponse
     */
    @PutMapping("/{id}")
    public RatingResponse update(@PathVariable String id, @RequestBody @Valid RatingRequest ratingRequest) {
        Rating rating = convert(ratingRequest);
        rating.setId(id);
        return convert(ratingService.update(rating));
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
     * This type of request allows to delete the object by id.
     *
     * @param id the object identifier
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        ratingService.deleteById(id);
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
    @GetMapping("/user/{userId}")
    public List<PostResponse> findAllPostsByUser(@PathVariable String userId,
                                                 @RequestParam(required = false) Integer limit,
                                                 @RequestParam(required = false) Integer offset) {
        return ratingService.findAllPostsWithRatingsByUserId(userId, limit, offset).stream()
                .map(PostConverter::convert)
                .collect(Collectors.toList());
    }
}
