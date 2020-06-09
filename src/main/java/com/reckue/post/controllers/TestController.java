package com.reckue.post.controllers;

import com.reckue.post.transfers.TagResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
public class TestController {

    @GetMapping("/")
    public TagResponse hello() {
        return TagResponse.builder().id("1").name("java").build();
    }
}
