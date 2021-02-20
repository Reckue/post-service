package com.reckue.post.controller;

import com.reckue.post.generated.controller.PostsApi;
import com.reckue.post.generated.controller.dto.PostRequestDto;
import com.reckue.post.generated.controller.dto.PostResponseDto;
import com.reckue.post.model.Post;
import com.reckue.post.service.PostService;
import com.reckue.post.util.converter.PostConverter;
import com.reckue.post.util.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Class PostController represents simple REST-Controller.
 *
 * @author Kamila Meshcheryakova
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController implements PostsApi {

    private final PostService postService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/posts")
    @Override
    public ResponseEntity<PostResponseDto> createPost(@Valid PostRequestDto postRequestDto) {
        Post post = PostConverter.convertToModel(postRequestDto);
        post.setUserId(CurrentUser.getId());
        Post storedPost = postService.create(post);
        return new ResponseEntity<>(PostConverter.convertToDto(storedPost), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PostResponseDto> updatePost(String id, @Valid PostRequestDto postRequestDto) {
        Post post = PostConverter.convertToModel(postRequestDto);
        post.setId(id);
        return ResponseEntity.ok(PostConverter.convertToDto(postService.update(post)));
    }

    @Override
    public ResponseEntity<PostResponseDto> getPostById(String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok(PostConverter.convertToDto(post));
    }

    @GetMapping(value = "/posts")
    @Override
    public ResponseEntity<List<PostResponseDto>> getPosts(@Valid Integer limit, @Valid Integer offset,
                                                          @Valid String sort, @Valid Boolean desc) {
        List<Post> postLIst = postService.findAll();
        return ResponseEntity.ok(PostConverter.convertToDtoList(postLIst));
    }

    @Override
    public ResponseEntity<Void> deletePostById(String id) {
        postService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
