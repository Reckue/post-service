package com.reckue.post.repositories;

import com.reckue.post.models.ImageNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageNodeRepository extends MongoRepository<ImageNode, String> {
}
