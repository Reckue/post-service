package com.reckue.post.model.type;

import com.reckue.post.model.node.*;

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
