package com.reckue.post.repositories;

import com.reckue.post.models.Node;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface NodeRepository is responsible for connecting to MongoDB.
 *
 * @author Kamila Meshcheryakova
 */
@Repository
public interface NodeRepository extends MongoRepository<Node, String> {
}
