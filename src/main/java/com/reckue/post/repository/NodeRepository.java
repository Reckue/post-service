package com.reckue.post.repository;

import com.reckue.post.model.Node;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface NodeRepository is responsible for connecting to MongoDB.
 *
 * @author Kamila Meshcheryakova
 */
@Repository
public interface NodeRepository extends MongoRepository<Node, String> {

    /**
     * This method is used to get a list of nodes by parent id.
     *
     * @param parentId parent node identificator
     * @return list of objects of class Node
     */
    List<Node> findAllByParentId(String parentId);

    /**
     * This method is used to get a list of nodes by user id.
     *
     * @param userId user identificator
     * @return list of objects of class Node
     */
    List<Node> findAllByUserId(String userId);
}
