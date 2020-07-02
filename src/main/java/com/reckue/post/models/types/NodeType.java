package com.reckue.post.models.types;

import com.reckue.post.models.nodes.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Enum NodeType represents enumeration of types of content that are associated with node.
 *
 * @author Iveri Narozashvili
 */
@SuppressWarnings("unused")
public enum NodeType {

    TEXT("text"),
    IMAGE("image"),
    VIDEO("video"),
    CODE("code"),
    LIST("list"),
    AUDIO("audio"),
    POLL("poll");

    public String name;

    NodeType(String name) {
        this.name = name;
    }

//    public Class<?> getClassByType() {
//        Map<Integer, Class<? extends Parent>> map = Map.ofEntries(
//                entry("text", TextNode.class),
//                entry("image", ImageNode.class),
//                entry("video", VideoNode.class),
//                entry("code", CodeNode.class),
//                entry("list", ListNode.class),
//                entry("audio", AudioNode.class),
//                entry("poll", PollNode.class)
//        );
//
//        Arrays.stream(NodeType.values())
//                .filter(type -> type.name().equals(map.get(type.name).toString()))
//                .findFirst()
//                .orElse(NodeType.TEXT);
//
//        return TextNode.class;
//    }
}
