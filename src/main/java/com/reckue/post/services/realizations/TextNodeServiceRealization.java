package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.TextNode;
import com.reckue.post.repositories.TextNodeRepository;
import com.reckue.post.services.TextNodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TextNodeServiceRealization implements TextNodeService {

    @Autowired
    private TextNodeRepository textNodeRepository;

    @Override
    public TextNode create (TextNode textNode) {
        TextNode savedTextNode;
        savedTextNode = textNode;
        savedTextNode.setId(UUID.randomUUID().toString());
        return textNodeRepository.save(textNode);
    }

    @Override
    public TextNode update(TextNode textNode) {

        TextNode savedTextNode;

        if (textNode.getId() != null && textNodeRepository.existsById(textNode.getId())) {

            savedTextNode = textNodeRepository.findById(textNode.getId()).get();
            savedTextNode.setContent(textNode.getContent());
        } else {
            throw new ModelNotFoundException("TextNodeNotFound by id");
        }
        return textNodeRepository.save(savedTextNode);
    }


    @Override
    public List<TextNode> findAll(int limit, int offset, String sort, boolean desc) {
//        List<TextNode> textNodes =

        return textNodeRepository.findAll().stream()
                .limit(limit)
                .skip(offset)
                .sorted(Comparator.comparing(TextNode::getId, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public TextNode findById(String id) {
        return textNodeRepository.findById(id).orElseThrow(
                ()-> new ModelNotFoundException("TextNodeNotFound by id"));
    }

    @Override
    public void deleteById(String id) {
        if(textNodeRepository.existsById(id)) {
            textNodeRepository.deleteById(id);
        } else {
            throw new RuntimeException("TextNodeNotFound by id");
        }
    }
}
