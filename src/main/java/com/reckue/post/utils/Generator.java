package com.reckue.post.utils;

import java.util.UUID;

/**
 * Class Generator generates string.
 */
public class Generator {

    /**
     * Generate a random string to identify the object.
     *
     * @return generated string
     */
    public static String id() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Generates a string to be passed to it.
     *
     * @param word write a description
     * @return generated string based on passed string
     */
    public static String id(String word) {
        return UUID.fromString(word).toString().replace("-", "");
    }
}
