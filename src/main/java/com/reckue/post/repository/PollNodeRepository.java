package com.reckue.post.repository;

import com.reckue.post.model.node.PollNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface PollNodeRepository is responsible for connecting to MongoDB.
 *
 * @author Viktor Grigoriev
 */
@Repository
public interface PollNodeRepository extends MongoRepository<PollNode, String> {
}
