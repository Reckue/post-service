package com.reckue.post.utils;

import java.util.UUID;

public class Generator {

    public static String id() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String id(String word) {
        return UUID.fromString(word).toString().replace("-", "");
    }
}
