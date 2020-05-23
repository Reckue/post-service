package com.reckue.post.repositories;

import com.reckue.post.models.ListNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListNodeRepository extends MongoRepository<ListNode, String> {
}
