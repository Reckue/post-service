package com.reckue.post.service.impl;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.exception.model.post.PostNotFoundException;
import com.reckue.post.exception.model.rating.RatingNotFoundException;
import com.reckue.post.model.Post;
import com.reckue.post.model.Rating;
import com.reckue.post.repository.PostRepository;
import com.reckue.post.repository.RatingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class RatingServiceImplTest represents test for RatingService class.
 *
 * @author Kamila Meshcheryakova
 */
@SuppressWarnings("unused")
public class RatingServiceImplTest extends PostServiceApplicationTests {
    private Rating rating1;
    private Rating rating2;
    private Rating rating3;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    private void createRatings() {
        rating1 = Rating.builder()
                .id("1")
                .userId("1a35")
                .postId("1ft2")
                .build();
        rating2 = Rating.builder()
                .id("2")
                .userId("2da3")
                .postId("3dt5")
                .build();
        rating3 = Rating.builder()
                .id("3")
                .userId("7gd3")
                .postId("8uv2")
                .build();
    }

    @Test
    public void findAll() {
        List<Rating> nodes = Stream.of(rating1, rating2, rating3).collect(Collectors.toList());
        when(ratingRepository.findAll()).thenReturn(nodes);

        assertEquals(nodes, ratingService.findAll());
    }

    @Disabled
    public void create() {
        Post post = Post.builder()
                .id("2rs5")
                .build();
        when(postRepository.save(post)).thenReturn(post);

        Rating rating = Rating.builder()
                .id("4")
                .userId("5tf4")
                .postId(post.getId())
                .build();
        when(ratingRepository.save(rating)).thenReturn(rating);
        doReturn(true).when(postRepository).existsById(post.getId());

        assertEquals(rating, ratingService.create(rating, new HashMap<>()));
    }

    @Disabled
    public void createIfNotFound() {
        when(ratingRepository.existsById(rating1.getId())).thenReturn(false);
        doReturn(false).when(postRepository).existsById(rating1.getPostId());

        Exception exception = assertThrows(PostNotFoundException.class,
                () -> ratingService.create(rating1, new HashMap<>()));

        assertEquals("Post by id '" + rating1.getPostId() + "' is not found", exception.getMessage());
    }

    @Disabled
    public void update() {
        Rating ratingRequest = Rating.builder()
                .id("1")
                .userId("asad")
                .postId("asdasdfdf")
                .build();
        Rating ratingOne = Rating.builder()
                .id("1")
                .userId("1a35")
                .postId("1ft2")
                .build();
        when(ratingRepository.findById(ratingRequest.getId())).thenReturn(Optional.of(ratingOne));
        when(ratingRepository.save(ratingOne)).thenReturn(ratingOne);

        ratingService.update(ratingRequest, new HashMap<>());

        Assertions.assertAll(
                () -> assertEquals(ratingOne.getUserId(), ratingOne.getUserId()),
                () -> assertEquals(ratingOne.getPostId(), ratingOne.getPostId())
        );
    }

    @Test
    public void updateWithNullId() {
        Rating ratingOne = Rating.builder()
                .build();

        assertThrows(ReckueIllegalArgumentException.class, () -> ratingService.update(ratingOne, new HashMap<>()));
    }

    @Test
    public void updateWithExistId() {
        when(ratingRepository.existsById(rating1.getId())).thenReturn(false);
        when(ratingRepository.save(rating1)).thenReturn(rating1);

        assertThrows(RatingNotFoundException.class, () -> ratingService.update(rating1, new HashMap<>()));
    }

    @Disabled
    public void deleteById() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating1);
        when(ratingRepository.existsById(rating1.getId())).thenReturn(true);
        doAnswer(invocation -> {
            ratings.remove(rating1);
            return null;
        }).when(ratingRepository).deleteById(rating1.getId());
        ratingService.deleteById(rating1.getId(), new HashMap<>());

        assertEquals(0, ratings.size());
    }

    @Test
    public void deleteByIdWithException() {
        Exception exception = assertThrows(RatingNotFoundException.class,
                () -> ratingService.deleteById(rating1.getId(), new HashMap<>()));
        assertEquals("Rating by id '" + rating1.getId() + "' is not found", exception.getMessage());
    }
}
