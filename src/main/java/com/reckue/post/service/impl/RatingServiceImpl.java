package com.reckue.post.service.impl;

import com.reckue.post.exception.ReckueAccessDeniedException;
import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.exception.model.post.PostNotFoundException;
import com.reckue.post.exception.model.rating.RatingNotFoundException;
import com.reckue.post.exception.model.user.UserNotFoundException;
import com.reckue.post.model.Post;
import com.reckue.post.model.Rating;
import com.reckue.post.model.Role;
import com.reckue.post.processor.notnull.NotNullArgs;
import com.reckue.post.repository.PostRepository;
import com.reckue.post.repository.RatingRepository;
import com.reckue.post.service.RatingService;
import com.reckue.post.util.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class RatingServiceImpl represents realization of RatingService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final PostRepository postRepository;

    @Override
    @NotNullArgs
    public Rating create(Rating rating) {
        rating.setUserId(CurrentUser.getId());
        validateCreatingRating(rating);

        if (ratingRepository.existsByUserIdAndPostId(rating.getUserId(), rating.getPostId())) {
            Rating existRating = ratingRepository.findByUserIdAndPostId(rating.getUserId(), rating.getPostId());
            ratingRepository.deleteById(existRating.getId());
            return existRating;
        }

        return ratingRepository.save(rating);
    }

    public void validateCreatingRating(Rating rating) {
        if (!postRepository.existsById(rating.getPostId())) {
            throw new PostNotFoundException(rating.getPostId());
        }
    }

    @Override
    public Rating update(Rating rating) {
        if (rating.getId() == null) {
            throw new ReckueIllegalArgumentException("The parameter is null");
        }
        if (!CurrentUser.getId().equals(rating.getUserId()) && !CurrentUser.getRoles().contains(Role.ADMIN)) {
            throw new ReckueAccessDeniedException("The operation is forbidden");
        }
        Rating savedRating = ratingRepository
                .findById(rating.getId())
                .orElseThrow(() -> new RatingNotFoundException(rating.getId()));
        savedRating.setUserId(savedRating.getUserId());
        savedRating.setPostId(savedRating.getPostId());

        return ratingRepository.save(savedRating);
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

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

    public List<Rating> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<Rating> ratings = findAllBySortType(sort);
            Collections.reverse(ratings);
            return ratings;
        }
        return findAllBySortType(sort);
    }

    public List<Rating> findAllBySortType(String sort) {

        switch (sort) {
            case "createdDate":
                return findAllAndSortByCreatedDate();
            case "modificationDate":
                return findAllAndSortByModificationDate();
            case "id":
                return findAllAndSortById();
            default:
                throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
        }
    }

    public List<Rating> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(Rating::getId))
                .collect(Collectors.toList());
    }

    public List<Rating> findAllAndSortByCreatedDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Rating::getCreatedDate))
                .collect(Collectors.toList());
    }

    public List<Rating> findAllAndSortByModificationDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Rating::getModificationDate))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        if (!ratingRepository.existsById(id)) {
            throw new RatingNotFoundException(id);
        }
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            if (CurrentUser.getId().equals(rating.get().getUserId()) || CurrentUser.getRoles().contains(Role.ADMIN)) {
                ratingRepository.deleteById(id);
            } else {
                throw new ReckueAccessDeniedException("The operation is forbidden");
            }
        }
    }

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

}
