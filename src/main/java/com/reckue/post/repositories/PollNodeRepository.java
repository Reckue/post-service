package com.reckue.post.repositories;

import com.reckue.post.models.PollNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface PollNodeRepository for accessing a CRUD operation.
 *
 * @author Viktor Grigoriev
 */
@Repository
public interface PollNodeRepository extends MongoRepository<PollNode, String> {
}
