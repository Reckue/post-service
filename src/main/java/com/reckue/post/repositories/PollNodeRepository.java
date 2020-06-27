package com.reckue.post.repositories;

import com.reckue.post.models.PollNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollNodeRepository extends MongoRepository<PollNode, String> {
}
