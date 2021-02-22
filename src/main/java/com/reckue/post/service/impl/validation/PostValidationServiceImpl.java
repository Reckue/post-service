package com.reckue.post.service.impl.validation;

import com.reckue.post.model.Post;
import com.reckue.post.repository.PostRepository;
import com.reckue.post.service.validation.PostValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PostValidationServiceImpl implements PostValidationService {

    private final PostRepository postRepository;

    @Override
    public void validateStatusOnCreate(Post post) {
        validatePostName(post.getTitle());
    }

    @Override
    public void validateStatusOnUpdate(Post post) {
        // TODO Implement a method
    }

    @Override
    public void validatePostName(String postName) {
        Optional.ofNullable(postName).orElseThrow(() -> new RuntimeException("Title cannot be empty"));
        if (postRepository.countAllByTitle(postName) > 0) {
            throw new RuntimeException("Post with the same title already exists");
        }
    }
}
