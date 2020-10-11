package com.reckue.post.models.types;

import com.reckue.post.models.nodes.AudioNode;
import com.reckue.post.models.nodes.PollNode;

/**
 * Enum ParentType represents enumeration of the entity types to which the nodes belong.
 *
 * @author Artur Magomedov
 * @author Dmitrii Lebedev
 */
public enum ParentType {

    COMMENT(AudioNode.class),
    POST(PollNode.class);

    public Class<?> parentClass;

    ParentType(Class<?> parentClass) {
        this.parentClass = parentClass;
    }
}
