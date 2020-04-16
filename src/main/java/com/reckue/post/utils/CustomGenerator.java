package com.reckue.post.utils;

import java.util.UUID;

public class CustomGenerator {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
