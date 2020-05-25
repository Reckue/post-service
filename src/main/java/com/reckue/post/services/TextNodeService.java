package com.reckue.post.services;

import com.reckue.post.models.TextNode;

import java.util.List;

public interface TextNodeService {

    TextNode create(TextNode textNode);

    TextNode update(TextNode textNode);

    List<TextNode> findAll(int limit, int offset, String sort, boolean desc);

    TextNode findById(String id);

    void deleteById(String id);
}
