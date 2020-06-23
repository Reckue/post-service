package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Rating;
import com.reckue.post.repositories.RatingRepository;
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
public class RatingServiceRealizationTest extends PostServiceRealizationTest {
    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceRealization ratingService;

    @Test
    public void findAll() {
        Rating rating1 = Rating.builder()
                .id("1")
                .build();
        Rating rating2 = Rating.builder()
                .id("2")
                .build();
        List<Rating> nodes = Stream.of(rating1, rating2).collect(Collectors.toList());
        when(ratingRepository.findAll()).thenReturn(nodes);

        assertEquals(nodes, ratingService.findAll());
    }

    @Test
    public void create() {
        Rating rating = Rating.builder()
                .id("1")
                .build();
        when(ratingRepository.save(rating)).thenReturn(rating);

        assertEquals(rating, ratingService.create(rating));
    }

    @Test
    public void createIfExists() {
        Rating rating = Rating.builder()
                .id("1")
                .build();
        doReturn(true).when(ratingRepository).existsById(Mockito.anyString());
        Exception exception = assertThrows(ModelAlreadyExistsException.class, () -> ratingService.create(rating));

        assertEquals("Rating already exists", exception.getMessage());
    }

    @Test
    public void update() {
        Rating ratingOne = Rating.builder()
                .id("1")
                .build();
        when(ratingRepository.existsById(ratingOne.getId())).thenReturn(true);
        when(ratingRepository.save(ratingOne)).thenReturn(ratingOne);

        assertEquals(ratingOne, ratingService.update(ratingOne));
    }

    @Test
    public void updateWithNullId() {
        Rating ratingOne = Rating.builder()
                .id("1")
                .build();

        assertThrows(IllegalArgumentException.class, () -> ratingService.update(ratingOne));
    }

    @Test
    public void updateWithExistId() {
        Rating ratingOne = Rating.builder()
                .id("1")
                .build();
        when(ratingRepository.existsById(ratingOne.getId())).thenReturn(false);
        when(ratingRepository.save(ratingOne)).thenReturn(ratingOne);

        assertThrows(ModelNotFoundException.class, () -> ratingService.update(ratingOne));
    }

    @Test
    public void findByIdWithException() {
        Rating ratingOne = Rating.builder()
                .id("1")
                .build();
        when(ratingRepository.findById(ratingOne.getId())).thenReturn(Optional.empty());

        assertThrows(ModelNotFoundException.class, () -> ratingService.findById(ratingOne.getId()));
    }

    @Test
    public void findById() {
        Rating ratingOne = Rating.builder()
                .id("1")
                .build();
        when(ratingRepository.findById(ratingOne.getId())).thenReturn(Optional.of(ratingOne));

        assertEquals(ratingOne, ratingService.findById(ratingOne.getId()));
    }

    @Test
    public void deleteById() {
        Rating ratingOne = Rating.builder()
                .id("1")
                .build();
        List<Rating> rating = new ArrayList<>();
        rating.add(ratingOne);
        when(ratingRepository.existsById(ratingOne.getId())).thenReturn(true);
        doAnswer(invocation -> {
            rating.remove(ratingOne);
            return null;
        }).when(ratingRepository).deleteById(ratingOne.getId());
        ratingService.deleteById(ratingOne.getId());

        assertEquals(0, rating.size());
    }

    @Test
    public void deleteByIdWithException() {
        Rating ratingOne = Rating.builder()
                .id("0")
                .build();
        Exception exception = assertThrows(ModelNotFoundException.class, () -> ratingService.deleteById(ratingOne.getId()));
        assertEquals("Rating by id " + ratingOne.getId() + " is not found", exception.getMessage());
    }
}
