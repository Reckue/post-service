package com.reckue.note.utils;

import java.util.UUID;

public class CustomUUID {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
