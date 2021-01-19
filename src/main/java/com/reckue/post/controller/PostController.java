package com.reckue.post.controller;

import com.reckue.post.generated.controller.PostsApi;
import com.reckue.post.generated.controller.dto.PostRequestDto;
import com.reckue.post.generated.controller.dto.PostResponseDto;
import com.reckue.post.model.Post;
import com.reckue.post.service.PostService;
import com.reckue.post.util.converter.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    public PostResponseDto create(@Valid PostRequestDto postRequest) {
        Post postModel = PostConverter.convertToModel(postRequest);
        return PostConverter.convertToDto(postService.create(postModel));
    }

    @Override
    public ResponseEntity<PostResponseDto> updatePost(String id, PostRequestDto postRequest) {
        Post post = PostConverter.convertToModel(postRequest);
        post.setId(id);
        return PostConverter.convertToDto(postService.update(post));
    }

    public PostResponseDto findById(@PathVariable String id) {
        return PostConverter.convertToDto(postService.findById(id));
    }

    public List<PostResponseDto> findByTitle(@PathVariable String title) {
        return postService.findAllByTitle(title).stream()
                .map(PostConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> findAllByUserId(@PathVariable String userId,
                                              @RequestParam(required = false) Integer limit,
                                              @RequestParam(required = false) Integer offset) {
        return postService.findAllByUserId(userId, limit, offset).stream()
                .map(PostConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> findAll(@RequestParam(required = false) Integer limit,
                                      @RequestParam(required = false) Integer offset,
                                      @RequestParam(required = false) String sort,
                                      @RequestParam(required = false) Boolean desc) {
        return postService.findAll(limit, offset, sort, desc).stream()
                .map(PostConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteById(@PathVariable String id) {
        postService.deleteById(id);
    }
}
