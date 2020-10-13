package com.reckue.post.models.types;

import com.reckue.post.models.Comment;
import com.reckue.post.models.Post;
import com.reckue.post.models.nodes.PollNode;

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
