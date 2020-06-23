package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Post;
import com.reckue.post.models.Rating;
import com.reckue.post.repositories.RatingRepository;
import com.reckue.post.services.RatingService;
import com.reckue.post.utils.Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class RatingServiceRealization represents realization of RatingService.
 *
 * @author Iveri Narozashvili
 */
@Service
@RequiredArgsConstructor
public class RatingServiceRealization implements RatingService {
    private final RatingRepository ratingRepository;

    /**
     * This method is used to create an object of class Rating.
     * Throws {@link ModelAlreadyExistsException} in case if such object already exists.
     *
     * @param rating object of class Rating
     * @return rating object of class Rating
     */
    @Override
    public Rating create(Rating rating) {
        rating.setId(Generator.id());
        if (!ratingRepository.existsById(rating.getId())) {
            return ratingRepository.save(rating);
        } else {
            throw new ModelAlreadyExistsException("Rating already exists");
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
        if (!ratingRepository.existsById(rating.getId())) {
            throw new ModelNotFoundException("Rating by id " + rating.getId() + " is not found");
        }
        Rating savedRating = Rating.builder()
                .id(rating.getId())
                .userId(rating.getUserId())
                .build();
        return ratingRepository.save(savedRating);
    }

    /**
     * This method is used to get all objects of class Post.
     *
     * @return list of objects of class Post
     */
    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
        return null;
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
