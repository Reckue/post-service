package com.reckue.post.models.types;

/**
 * Enum ParentType represents enumeration of the entity types to which the nodes belong.
 *
 * @author Artur Magomedov
 */
public enum ParentType {

    COMMENT("comment"),
    POST("post");

    public String name;

    ParentType(String name) {
        this.name = name;
    }
}
