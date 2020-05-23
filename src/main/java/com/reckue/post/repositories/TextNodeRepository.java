package com.reckue.post.repositories;

import com.reckue.post.models.TextNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextNodeRepository extends MongoRepository<TextNode, String> {
}
