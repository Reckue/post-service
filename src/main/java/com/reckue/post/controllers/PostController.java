package com.reckue.post.controllers;

import com.reckue.post.models.Post;
import com.reckue.post.services.PostService;
import com.reckue.post.transfers.PostRequest;
import com.reckue.post.transfers.PostResponse;
import com.reckue.post.utils.converters.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class PostController represents simple REST-Controller.
 *
 * @author Kamila Meshcheryakova
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostResponse save(@RequestBody PostRequest postRequest) {
        return Converter.convert(postService.create(Converter.
                convert(postRequest, Post.class)), PostResponse.class);
    }

    @PutMapping("/{id}")
    public PostResponse update(@PathVariable String id, @RequestBody PostRequest postRequest) {
        Post post = Converter.convert(postRequest, Post.class);
        post.setId(id);
        return Converter.convert(postService.update(post), PostResponse.class);
    }

    @GetMapping("/{id}")
    public PostResponse getById(@PathVariable String id) {
        return Converter.convert(postService.findById(id), PostResponse.class);
    }

    @GetMapping
    public List<PostResponse> getAll() {
        return postService.findAll().stream()
                .map(post -> Converter.convert(post, PostResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<PostResponse> getAll(int limit, int offset, String sort, boolean desc) {
        return postService.findAll(limit, offset, sort, desc).stream()
                .map(post -> Converter.convert(post, PostResponse.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("{/id}")
    public void deleteById(@PathVariable String id) {
        postService.deleteById(id);
    }

}
