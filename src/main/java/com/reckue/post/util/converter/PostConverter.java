package com.reckue.post.util.converter;

import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.model.Node;
import com.reckue.post.model.Post;
import com.reckue.post.generated.controller.dto.NodeResponseDto;
import com.reckue.post.generated.controller.dto.PostRequestDto;
import com.reckue.post.generated.controller.dto.PostResponseDto;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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
        if (postRequest == null) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }

        List<Node> nodes = new ArrayList<>();
        if (postRequest.getNodes() != null) {
            nodes = postRequest.getNodes().stream()
                    .map(NodeConverter::convertToModel)
                    .collect(Collectors.toList());
        }

        return Post.builder()
                .title(postRequest.getTitle())
                .nodes(nodes)
                .source(postRequest.getSource())
                .tags(postRequest.getTags())
                .status(postRequest.getStatus())
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
        if (post == null) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }

        List<NodeResponseDto> nodes = new ArrayList<>();
        if (post.getNodes() != null) {
            nodes = post.getNodes().stream()
                    .map(NodeConverter::convertToDto)
                    .collect(Collectors.toList());
        }

        return PostResponseDto.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .nodes(nodes)
                .source(post.getSource())
                .tags(post.getTags())
                .createdDate(post.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .modificationDate(post.getModificationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(post.getStatus())
                .build();
    }
}
