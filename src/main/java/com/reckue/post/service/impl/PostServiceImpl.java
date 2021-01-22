package com.reckue.post.service.impl;

import com.reckue.post.exception.ReckueAccessDeniedException;
import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.exception.model.post.PostNotFoundException;
import com.reckue.post.model.Node;
import com.reckue.post.model.Post;
import com.reckue.post.model.type.ParentType;
import com.reckue.post.model.type.PostStatusType;
import com.reckue.post.processor.notnull.NotNullArgs;
import com.reckue.post.repository.NodeRepository;
import com.reckue.post.repository.PostRepository;
import com.reckue.post.transfer.dto.post.PostFilterRequest;
import com.reckue.post.transfer.dto.post.PostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class PostServiceImpl represents realization of PostService.
 *
 * @author Kamila Meshcheryakova
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class PostServiceImpl {

    private final PostRepository postRepository;
    private final NodeRepository nodeRepository;

    @Transactional
    public Post create(PostRequest postDto, Map<String, Object> tokenInfo) {
        String userId = (String) tokenInfo.get("userId"); // можно получать из хедера
		Post newPost = Post.builder()
				.userId(userId)
				.title(postDto.getTitle())
				.source(postDto.getSource())
				.tags(postDto.getTags())
				.status(PostStatusType.DRAFT)
				.build();
		log.info("creating post: " + newPost);
		return postRepository.save(newPost);
    }

	@Transactional
	public void publish(String postId, Map<String, Object> tokenInfo) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException(postId));
		if (!tokenInfo.get("userId").equals(post.getUserId())
				&& !tokenInfo.get("authorities").equals("ROLE_ADMIN")) {
			throw new ReckueAccessDeniedException("The operation is forbidden");
		}
    	post.setStatus(PostStatusType.PUBLISHED);
	}

    @Transactional
    public Post update(String postId, PostRequest postDto, Map<String, Object> tokenInfo) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
		if (!tokenInfo.get("userId").equals(post.getUserId())
				&& !tokenInfo.get("authorities").equals("ROLE_ADMIN")) {
			throw new ReckueAccessDeniedException("The operation is forbidden");
		}
		post.setTitle(postDto.getTitle());
        post.setSource(postDto.getSource());
        post.setTags(postDto.getTags());
        post.setStatus(postDto.getStatus());
        return postRepository.save(post);
    }

	@Transactional(readOnly = true)
    public Post findById(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        post.setNodes(nodeRepository.findAllByParentId(id));
        return post;
    }

    @Transactional(readOnly = true)
	public Page<Post> findAll(PostFilterRequest request, Pageable pageable) {
    	// пока что фильтр не используется
		// в выдаче должны быть только опубликованные посты
		Page<Post> posts = postRepository.findAll(pageable);
		if (request.getNodesRequired() != null && request.getNodesRequired()) {
			posts.getContent().forEach(post -> post.setNodes(nodeRepository.findAllByParentId(post.getId())));
		}
		return posts;
	}

	@Transactional
    public void deleteById(String postId, Map<String, Object> tokenInfo) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException(postId));
		if (!tokenInfo.get("userId").equals(post.getUserId())
				&& !tokenInfo.get("authorities").equals("ROLE_ADMIN")) {
			throw new ReckueAccessDeniedException("The operation is forbidden");
		}
		post.setStatus(PostStatusType.DELETED);
		postRepository.save(post);
    }

    @Deprecated
    public void deleteAll() {
        postRepository.deleteAll();
    }
}
