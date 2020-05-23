package com.reckue.post.repositories;

import com.reckue.post.models.CodeNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeNodeRepository extends MongoRepository<CodeNode, String> {
}
