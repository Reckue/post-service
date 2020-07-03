package com.reckue.post.repositories;

import com.reckue.post.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface PostRepository is responsible for connecting to MongoDB.
 *
 * @author Kamila Meshcheryakova
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
