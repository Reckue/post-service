package com.reckue.post.model.type;

import com.reckue.post.model.Comment;
import com.reckue.post.model.Post;

/**
 * Enum ParentType represents enumeration of the entity types to which the nodes belong.
 *
 * @author Artur Magomedov
 * @author Dmitrii Lebedev
 */
public enum ParentType {

    COMMENT(Comment.class),
    POST(Post.class);

    public Class<?> parentClass;

    ParentType(Class<?> parentClass) {
        this.parentClass = parentClass;
    }
}
