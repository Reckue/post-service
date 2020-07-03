package com.reckue.post.repositories;

import com.reckue.post.models.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface TagRepository is responsible for connecting to MongoDB.
 *
 * @author Kamila Meshcheryakova
 */
@Repository
public interface TagRepository extends MongoRepository<Tag, String> {
}
