package com.reckue.post.service;

import com.reckue.post.model.Node;
import org.springframework.data.domain.Page;

public interface NodeService {

    Node create(Node node);

    Page<Node> findAll();

    Page<Node> findAll(Integer limit, Integer offset, String sort, Boolean desc);

    Node findById(String id);

    Node update(Node node);

    void deleteById(String id);

}
