package com.reckue.post.repositories;

import com.reckue.post.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface CommentRepository is responsible for connecting to MongoDB.
 *
 * @author Artur Magomedov
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
