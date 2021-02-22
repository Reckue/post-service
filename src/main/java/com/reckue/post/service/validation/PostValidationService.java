package com.reckue.post.service.validation;

import com.reckue.post.model.Post;

public interface PostValidationService {

    void validateStatusOnCreate(Post post);

    void validateStatusOnUpdate(Post post);

    void validatePostName(String postName);

}
