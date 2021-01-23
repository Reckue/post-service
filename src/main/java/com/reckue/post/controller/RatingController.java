//package com.reckue.post.controller;
//
//import com.reckue.post.controller.api.RatingApi;
//import com.reckue.post.model.Rating;
//import com.reckue.post.service.RatingService;
//import com.reckue.post.transfer.PostRatingResponse;
//import com.reckue.post.transfer.PostResponse;
//import com.reckue.post.transfer.RatingRequest;
//import com.reckue.post.transfer.RatingResponse;
//import com.reckue.post.util.converter.PostConverter;
//import com.reckue.post.util.converter.RatingConverter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.reckue.post.util.converter.RatingConverter.convert;
//
///**
// * Class RatingController is responsible for processing incoming requests.
// *
// * @author Iveri Narozashvili
// */
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(value = "/rating")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//public class RatingController implements RatingApi {
//
//    private final RatingService ratingService;
//
//    // TODO: add postId as PathVariable and delete it from request (you don't need to enter a postId in update method)
//    @PostMapping
//    public RatingResponse create(@RequestBody @Valid RatingRequest ratingRequest,
//                                 HttpServletRequest request) {
//        return convert(ratingService.create(convert(ratingRequest)));
//    }
//
//    @PutMapping("/{id}")
//    public RatingResponse update(@PathVariable String id,
//                                 @RequestBody @Valid RatingRequest ratingRequest,
//                                 HttpServletRequest request) {
//        Rating rating = convert(ratingRequest);
//        rating.setId(id);
//        return convert(ratingService.update(rating));
//    }
//
//    @GetMapping
//    public List<RatingResponse> findAll(@RequestParam(required = false) Integer limit,
//                                        @RequestParam(required = false) Integer offset,
//                                        @RequestParam(required = false) String sort,
//                                        @RequestParam(required = false) Boolean desc) {
//        return ratingService.findAll(limit, offset, sort, desc).stream()
//                .map(RatingConverter::convert)
//                .collect(Collectors.toList());
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable String id, HttpServletRequest request) {
//        ratingService.deleteById(id);
//    }
//
//    @GetMapping("/post/{postId}")
//    public PostRatingResponse getQuantityOfRatingsToPost(@PathVariable String postId) {
//        return PostRatingResponse.builder()
//                .count(ratingService.getRatingsCountByPostId(postId))
//                .build();
//    }
//
//    @GetMapping("/post/{userId}")
//    public List<PostResponse> findAllPostsByUser(@PathVariable String userId,
//                                                 @RequestParam(required = false) Integer limit,
//                                                 @RequestParam(required = false) Integer offset) {
//        return ratingService.findAllPostsWithRatingsByUserId(userId, limit, offset).stream()
//                .map(PostConverter::convertToDto)
//                .collect(Collectors.toList());
//    }
//}
