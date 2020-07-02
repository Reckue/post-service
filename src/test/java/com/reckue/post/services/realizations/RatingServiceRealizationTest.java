package com.reckue.post.services.realizations;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Post;
import com.reckue.post.models.Rating;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class RatingServiceRealizationTest represents test for RatingService class.
 *
 * @author Iveri Narozashvili
 */
@SuppressWarnings("unused")
public class RatingServiceRealizationTest extends PostServiceApplicationTests {
    private Rating rating1;
    private Rating rating2;
    private Rating rating3;
    private Post post;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private RatingServiceRealization ratingService;

    @BeforeEach
    private void createNeeded() {
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

    /*
    @Test
    public void create() {
        post = Post.builder()
                .id("2rs5")
                .build();
        postRepository.save(post);
        Rating rating = Rating.builder()
                .id("4")
                .userId("5tf4")
                .postId(post.getId())
                .build();
        when(ratingRepository.save(rating)).thenReturn(rating);

        assertEquals(rating, ratingService.create(rating));
    }
     */

    @Test
    public void createIfExists() {
        doReturn(true).when(ratingRepository).existsById(Mockito.anyString());
        Exception exception = assertThrows(ModelAlreadyExistsException.class, () -> ratingService.create(rating1));

        assertEquals("Rating already exists", exception.getMessage());
    }

    @Test
    public void createIfNotFound() {
        when(ratingRepository.existsById(rating1.getId())).thenReturn(false);
        Exception exception = assertThrows(ModelNotFoundException.class, () -> ratingService.create(rating1));

        assertEquals("Post identifier '" + rating1.getPostId() + "' is not found", exception.getMessage());
    }
    /*
    @Test
    public void update() {
        post = Post.builder()
                .id("2rs5")
                .build();
        postRepository.save(post);
        Rating ratingOne = Rating.builder()
                .id(rating1.getId())
                .userId(rating1.getUserId())
                .postId(rating1.getPostId())
                .build();
        when(ratingRepository.existsById(ratingOne.getId())).thenReturn(true);
        when(ratingRepository.save(ratingOne)).thenReturn(ratingOne);

        assertEquals(ratingOne, ratingService.update(ratingOne));
    }
     */

    @Test
    public void updateWithNullId() {
        Rating ratingOne = Rating.builder()
                .build();

        assertThrows(IllegalArgumentException.class, () -> ratingService.update(ratingOne));
    }

    @Test
    public void updateWithExistId() {
        when(ratingRepository.existsById(rating1.getId())).thenReturn(false);
        when(ratingRepository.save(rating1)).thenReturn(rating1);

        assertThrows(ModelNotFoundException.class, () -> ratingService.update(rating1));
    }

    @Test
    public void findByIdWithException() {
        when(ratingRepository.findById(rating1.getId())).thenReturn(Optional.empty());

        assertThrows(ModelNotFoundException.class, () -> ratingService.findById(rating1.getId()));
    }

    @Test
    public void findById() {
        when(ratingRepository.findById(rating1.getId())).thenReturn(Optional.of(rating1));

        assertEquals(rating1, ratingService.findById(rating1.getId()));
    }

    @Test
    public void deleteById() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating1);
        when(ratingRepository.existsById(rating1.getId())).thenReturn(true);
        doAnswer(invocation -> {
            ratings.remove(rating1);
            return null;
        }).when(ratingRepository).deleteById(rating1.getId());
        ratingService.deleteById(rating1.getId());

        assertEquals(0, ratings.size());
    }

    @Test
    public void deleteByIdWithException() {
        Exception exception = assertThrows(ModelNotFoundException.class,
                () -> ratingService.deleteById(rating1.getId()));
        assertEquals("Rating by id " + rating1.getId() + " is not found", exception.getMessage());
    }
}
