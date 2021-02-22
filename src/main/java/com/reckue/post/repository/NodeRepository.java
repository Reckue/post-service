package com.reckue.post.repository;

import com.reckue.post.model.Node;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends PagingAndSortingRepository<Node, String> {

}
