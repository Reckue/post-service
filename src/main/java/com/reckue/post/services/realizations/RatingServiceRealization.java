package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Rating;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.repositories.RatingRepository;
import com.reckue.post.services.RatingService;
import com.reckue.post.utils.Generator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class RatingServiceRealization represents realization of RatingService.
 * todo add method: int getRatingCountByPostId(String postId) {};
 *      add method: findAllPostsWithRatingByUserId (String userId, Integer limit, Integer offset);
 *      remove unnecessary methods;
 *      add sort by date instead of existing sort types.
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
        rating.setId(Generator.id());
        Date date = new Date();
        rating.setPublished(date.getTime());
        validateCreatingRating(rating);

        /*
        I need to implement "add to method "create": if such userId already exists then delete the rating".

        If rating with that userId and that postId already exists we delete existing rating
        and also delete new rating and get the error that rating not found.
        Maybe we need to save new rating?
         */
        if (ratingRepository.existsByUserIdAndPostId(rating.getUserId(), rating.getPostId())) {
            Rating existRating = ratingRepository.findByUserIdAndPostId(rating.getUserId(), rating.getPostId());
            ratingRepository.deleteById(existRating.getId());
            deleteById(rating.getId());
        }

        return ratingRepository.save(rating);
    }

    /**
     * This method is used to check rating validation.
     * Throws {@link ModelAlreadyExistsException} in case if such object already exists.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param rating object of class Rating
     */
    public void validateCreatingRating(Rating rating) {
        if (ratingRepository.existsById(rating.getId())) {
            throw new ModelAlreadyExistsException("Rating already exists");
        }
        if (!postRepository.existsById(rating.getPostId())) {
            throw new ModelNotFoundException("Post identifier '" + rating.getPostId() + "' is not found");
        }
    }

    /**
     * This method is used to update data in an object of class Rating.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link IllegalArgumentException} in case
     * if parameter equals null.
     *
     * @param rating object of class Rating
     * @return rating object of class Rating
     */

    @Override
    public Rating update(Rating rating) {
        if (rating.getId() == null) {
            throw new IllegalArgumentException("The parameter is null");
        }
        Rating existRating = ratingRepository.findById(rating.getId())
                .orElseThrow(() -> new ModelNotFoundException("Rating by id " + rating.getId() + " is not found"));

        Rating savedRating = Rating.builder()
                .id(existRating.getId())
                .userId(existRating.getUserId())
                .postId(existRating.getPostId())
                .published(existRating.getPublished())
                .build();
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
            throw new IllegalArgumentException("Limit or offset is incorrect");
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
     * @param sort type of sorting: userId, postId, default - id
     * @return list of objects of class Rating sorted by the selected parameter for sorting
     */
    public List<Rating> findAllBySortType(String sort) {

        switch (sort) {
            case "userId":
                return findAllAndSortByUserId();
            case "id":
                return findAllAndSortById();
            case "postId":
                return findAllAndSortByPostId();
        }
        throw new IllegalArgumentException("Such field as " + sort + " doesn't exist");
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
     * This method is used to sort objects by content.
     *
     * @return list of objects of class Rating sorted by userId
     */
    public List<Rating> findAllAndSortByUserId() {
        return findAll().stream()
                .sorted(Comparator.comparing(Rating::getUserId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by content.
     *
     * @return list of objects of class Rating sorted by postId
     */
    public List<Rating> findAllAndSortByPostId() {
        return findAll().stream()
                .sorted(Comparator.comparing(Rating::getPostId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return post object of class Rating
     */
    @Override
    public Rating findById(String id) {
        return ratingRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException("Rating by id " + id + " is not found"));
    }

    /**
     * This method is used to delete an object by id.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     */
    @Override
    public void deleteById(String id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("Rating by id " + id + " is not found");
        }
    }
}
