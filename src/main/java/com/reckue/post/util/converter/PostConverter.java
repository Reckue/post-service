package com.reckue.post.util.converter;

import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.generated.controller.dto.PostRequestDto;
import com.reckue.post.generated.controller.dto.PostResponseDto;
import com.reckue.post.generated.controller.dto.PostStatusTypeDto;
import com.reckue.post.model.Post;
import com.reckue.post.model.type.PostStatusType;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class PostConverter converts from PostRequest object to Post and Post object to PostResponse.
 *
 * @author Kamila Meshcheryakova
 */
public class PostConverter {

    /**
     * This method is used to convert from the object of class PostRequest
     * to the object of class Post.
     *
     * @param postRequest the object of class PostRequest
     * @return the object of class Post
     */
    public static Post convertToModel(PostRequestDto postRequest) {
        if (Objects.isNull(postRequest)) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }

        return Post.builder()
                .title(postRequest.getTitle())
                .nodes(Optional.ofNullable(postRequest.getNodes())
                        .orElse(new ArrayList<>()).stream()
                        .map(NodeConverter::convertToModel)
                        .collect(Collectors.toList()))
                .tags(postRequest.getTags())
                .status(Converter.convert(postRequest.getStatus(), PostStatusType.class))
                .build();
    }

    /**
     * This method is used to convert from the object of class Post
     * to the object of class PostResponse.
     *
     * @param post the object of class Post
     * @return the object of class PostResponse
     */
    public static PostResponseDto convertToDto(Post post) {
        if (Objects.isNull(post)) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }

        return PostResponseDto.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .nodes(Optional.ofNullable(post.getNodes())
                        .orElse(new ArrayList<>()).stream()
                        .map(NodeConverter::convertToDto)
                        .collect(Collectors.toList()))
                .tags(post.getTags())
                .createdDate(post.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .modificationDate(post.getModificationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(Converter.convert(post.getStatus(), PostStatusTypeDto.class))
                .build();
    }

    public static List<PostResponseDto> convertToDtoList(List<Post> posts) {
        return Optional.ofNullable(posts)
                .orElse(List.of()).stream()
                .map(PostConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
