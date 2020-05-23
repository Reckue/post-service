package com.reckue.post.repositories;

import com.reckue.post.models.VideoNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoNodeRepository extends MongoRepository<VideoNode, String> {
}
