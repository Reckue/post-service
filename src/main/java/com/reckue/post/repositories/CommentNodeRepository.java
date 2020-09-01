package com.reckue.post.repositories;

import com.reckue.post.models.CommentNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface CommentNodeRepository is responsible for connecting to MongoDB.
 *
 * @author Artur Magomedov
 */
@Repository
public interface CommentNodeRepository extends MongoRepository<CommentNode, String> {
}
