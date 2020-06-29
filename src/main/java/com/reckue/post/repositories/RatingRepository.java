package com.reckue.post.repositories;

import com.reckue.post.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface RatingRepository is responsible for connecting to MongoDB.
 *
 * @author Iveri Narozashvili
 */
@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
}
