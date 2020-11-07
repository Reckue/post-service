package com.reckue.post.repository;

import com.reckue.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface PostRepository is responsible for connecting to MongoDB.
 *
 * @author Kamila Meshcheryakova
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    /**
     * This method is used to get the objects by title.
     *
     * @param title object
     * @return list of objects of class Post
     */
    List<Post> findAllByTitle(String title);

    /**
     * This method is used to get a list of objects by user id.
     *
     * @param userId user identificator
     * @return list of objects of class Post
     */
    List<Post> findAllByUserId(String userId);
}
