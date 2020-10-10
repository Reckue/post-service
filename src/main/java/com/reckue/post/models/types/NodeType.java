package com.reckue.post.models.types;

import com.reckue.post.models.nodes.*;

/**
 * Enum NodeType represents enumeration of types of content that are associated with node.
 *
 * @author Iveri Narozashvili
 * @author Dmitrii Lebedev
 */
public enum NodeType {

    TEXT(TextNode.class),
    IMAGE(ImageNode.class),
    VIDEO(VideoNode.class),
    CODE(CodeNode.class),
    LIST(ListNode.class),
    AUDIO(AudioNode.class),
    POLL(PollNode.class);

    public Class<? extends Parent> nodeClass;

    NodeType(Class<? extends Parent> nodeClass) {
        this.nodeClass = nodeClass;
    }
}