package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.exceptions.models.post.PostNotFoundException;
import com.reckue.post.exceptions.models.rating.RatingNotFoundException;
import com.reckue.post.exceptions.models.user.UserNotFoundException;
import com.reckue.post.models.Post;
import com.reckue.post.models.Rating;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.repositories.RatingRepository;
import com.reckue.post.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class RatingServiceRealization represents realization of RatingService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class RatingServiceRealization implements RatingService {

    private final RatingRepository ratingRepository;
    private final PostRepository postRepository;

    /**
     * This method is used to create an object of class Rating using rating validation.
     *
     * @param rating object of class Rating
     * @return rating object of class Rating
     */
    @Override
    public Rating create(Rating rating) {
        validateCreatingRating(rating);

        if (ratingRepository.existsByUserIdAndPostId(rating.getUserId(), rating.getPostId())) {
            Rating existRating = ratingRepository.findByUserIdAndPostId(rating.getUserId(), rating.getPostId());
            ratingRepository.deleteById(existRating.getId());
            return existRating;
        }

        return ratingRepository.save(rating);
    }

    /**
     * This method is used to check rating validation.
     * Throws {@link PostNotFoundException} in case if such object isn't contained in database.
     *
     * @param rating object of class Rating
     */
    public void validateCreatingRating(Rating rating) {
        if (!postRepository.existsById(rating.getPostId())) {
            throw new PostNotFoundException(rating.getPostId());
        }
    }

    /**
     * This method is used to update data in an object of class Rating.
     * Throws {@link RatingNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link ReckueIllegalArgumentException} in case
     * if parameter equals null.
     *
     * @param rating object of class Rating
     * @return rating object of class Rating
     */
    @Override
    public Rating update(Rating rating) {
        if (rating.getId() == null) {
            throw new ReckueIllegalArgumentException("The parameter is null");
        }
        Rating savedRating = ratingRepository
                .findById(rating.getId())
                .orElseThrow(() -> new RatingNotFoundException(rating.getId()));
        savedRating.setUserId(savedRating.getUserId());
        savedRating.setPostId(savedRating.getPostId());
        return ratingRepository.save(savedRating);
    }

    /**
     * This method is used to get all objects of class Rating.
     *
     * @return list of objects of class Rating
     */
    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    /**
     * This method is used to get all objects of class Rating by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class Rating
     */
    @Override
    public List<Rating> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
        if (limit == null) limit = 10;
        if (offset == null) offset = 0;
        if (StringUtils.isEmpty(sort)) sort = "id";
        if (desc == null) desc = false;

        if (limit < 0 || offset < 0) {
            throw new ReckueIllegalArgumentException("Limit or offset is incorrect");
        }
        return findAllByTypeAndDesc(sort, desc).stream()
                .limit(limit)
                .skip(offset)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects in descending order by type.
     *
     * @param sort parameter for sorting
     * @param desc sorting descending
     * @return list of objects of class Rating sorted by the selected parameter for sorting
     * in descending order
     */
    public List<Rating> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<Rating> ratings = findAllBySortType(sort);
            Collections.reverse(ratings);
            return ratings;
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: createdDate, modificationDate, default - id
     * @return list of objects of class Rating sorted by the selected parameter for sorting
     */
    public List<Rating> findAllBySortType(String sort) {

        switch (sort) {
            case "createdDate":
                return findAllAndSortByCreatedDate();
            case "modificationDate":
                return findAllAndSortByModificationDate();
            case "id":
                return findAllAndSortById();
        }
        throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
    }

    /**
     * This method is used to sort objects by id.
     *
     * @return list of objects of class Rating sorted by id
     */
    public List<Rating> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(Rating::getId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by createdDate.
     *
     * @return list of objects of class Rating sorted by createdDate
     */
    public List<Rating> findAllAndSortByCreatedDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Rating::getCreatedDate))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by modificationDate.
     *
     * @return list of objects of class Rating sorted by modificationDate
     */
    public List<Rating> findAllAndSortByModificationDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Rating::getModificationDate))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to delete an object by id.
     * Throws {@link RatingNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     */
    @Override
    public void deleteById(String id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
        } else {
            throw new RatingNotFoundException(id);
        }
    }

    /**
     * This method is used to get the number of ratings to a single post.
     * Throws {@link PostNotFoundException} in case if such post id isn't contained in database.
     *
     * @param postId the post identifier
     * @return quantity of ratings to a post
     */
    @Override
    public int getRatingsCountByPostId(String postId) {
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException(postId);
        }
        if (postRepository.existsById(postId) && !ratingRepository.existsByPostId(postId)) {
            return 0;
        }
        List<Rating> ratings = ratingRepository.findByPostId(postId);
        return ratings.size();
    }

    /**
     * This method is used to get all posts with ratings by user id.
     * Throws {@link PostNotFoundException} in case if such user id isn't contained in database.
     *
     * @param userId the user identifier
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @return list of objects of class Post
     */
    @Override
    public List<Post> findAllPostsWithRatingsByUserId(String userId, Integer limit, Integer offset) {
        if (!ratingRepository.existsByUserId(userId)) {
            throw new UserNotFoundException(userId);
        }
        List<Rating> ratings = ratingRepository.findByUserId(userId);
        if (limit == null) limit = 10;
        if (offset == null) offset = 0;

        if (limit < 0 || offset < 0) {
            throw new ReckueIllegalArgumentException("Limit or offset is incorrect");
        }
        List<Post> posts = new ArrayList<>();
        for (Rating rating : ratings) {
            Post post = postRepository.findById(rating.getPostId())
                    .orElseThrow(PostNotFoundException::new);
            posts.add(post);
        }

        return posts.stream()
                .limit(limit)
                .skip(offset)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to delete all ratings.
     */
    @Deprecated
    @Override
    public void deleteAll() {
        ratingRepository.deleteAll();
    }
}
