package com.reckue.post.repository;

import com.reckue.post.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface CommentRepository is responsible for connecting to MongoDB.
 *
 * @author Artur Magomedov
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    /**
     * This method is used to get a list of comments by user id.
     *
     * @param userId user identificator
     * @return list of objects of class Comment
     */
    List<Comment> findAllByUserId(String userId);
}
