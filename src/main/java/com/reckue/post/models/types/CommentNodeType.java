package com.reckue.post.models.types;

/**
 * Enum CommentNodeType represents enumeration of types of content that are associated with comment node.
 *
 * @author Artur Magomedov
 */
public enum CommentNodeType {

    TEXT("text"),
    IMAGE("image"),
    VIDEO("video"),
    CODE("code"),
    LIST("list"),
    AUDIO("audio");

    public String name;

    CommentNodeType(String name) {
        this.name = name;
    }
}
