package com.reckue.post.service.impl;

import com.reckue.post.exception.ReckueAccessDeniedException;
import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.exception.model.post.PostNotFoundException;
import com.reckue.post.model.Node;
import com.reckue.post.model.Post;
import com.reckue.post.model.Role;
import com.reckue.post.model.type.ParentType;
import com.reckue.post.model.type.PostStatusType;
import com.reckue.post.processor.notnull.NotNullArgs;
import com.reckue.post.repository.NodeRepository;
import com.reckue.post.repository.PostRepository;
import com.reckue.post.service.NodeService;
import com.reckue.post.service.PostService;
import com.reckue.post.util.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class PostServiceImpl represents realization of PostService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final NodeRepository nodeRepository;
    private final NodeService nodeService;

    @Override
    @Transactional
    @NotNullArgs
    public Post create(Post post) {
        post.setUserId(CurrentUser.getId());

        validateOnCreatePost(post);
        validateOnCreateStatus(post);

        Post storedPost = (Post) SerializationUtils.clone(post);

        List<Node> nodeList = null;

        if (post.getNodes() != null) {
            nodeList = post.getNodes();
            post.setNodes(null);
            storedPost = postRepository.save(post);
            final String postId = storedPost.getId();

            nodeList.forEach(node -> {
                node.setParentId(postId);
                node.setParentType(ParentType.POST);
                nodeService.create(node);
            });
        }
        storedPost.setNodes(nodeList);
        return storedPost;
    }

    private void validateOnCreatePost(Post post) {
        if (post.getTitle() == null || post.getTitle().isEmpty()) {
            throw new RuntimeException("Title cannot be empty");
        }
    }

    private void validateOnCreateStatus(Post post) {
        if (post.getStatus() == null) {
            post.setStatus(PostStatusType.DRAFT);
            return;
        }
        if (post.getStatus() == PostStatusType.DRAFT) {
            return;
        }
        if (post.getStatus() == PostStatusType.PUBLISHED && !post.getNodes().isEmpty()) {
            return;
        }
        if (post.getStatus() == PostStatusType.BANNED) {
            throw new RuntimeException("Post can't be banned");
        }
        if (post.getStatus() == PostStatusType.PENDING) {
            throw new RuntimeException("Post can't be pending");
        }
        if (post.getStatus() == PostStatusType.DELETED) {
            throw new RuntimeException("Post can't be deleted");
        }
        if (post.getStatus() == PostStatusType.PUBLISHED && post.getNodes().isEmpty()) {
            throw new RuntimeException("Nodes are empty");
        }
    }

    @Override
    @Transactional
    @NotNullArgs
    public Post update(Post post) {
        if (post.getId() == null) {
            throw new ReckueIllegalArgumentException("The parameter is null");
        }

        if (!post.getNodes().isEmpty()) {
            post.getNodes().forEach(node -> {
                node.setParentId(post.getId());
                nodeService.create(node);
            });
        }
        validateOnUpdateStatus(post);
        Post savedPost = postRepository
                .findById(post.getId())
                .orElseThrow(() -> new PostNotFoundException(post.getId()));
        savedPost.setTitle(post.getTitle());
        savedPost.setNodes(post.getNodes());
        savedPost.setSource(post.getSource());
        savedPost.setTags(post.getTags());

        return postRepository.save(savedPost);
    }

    @NotNullArgs
    private void validateOnUpdateStatus(Post post) {
        if (post.getStatus() == null) {
            return;
        }
        if (post.getStatus() == PostStatusType.DRAFT) {
            postRepository.findById(post.getId()).ifPresent(p -> p.setStatus(PostStatusType.DRAFT));
            return;
        }
        if (post.getStatus() == PostStatusType.PUBLISHED) {
            postRepository.findById(post.getId()).ifPresent(p -> p.setStatus(PostStatusType.PUBLISHED));
            return;
        }
        if (post.getStatus() == PostStatusType.DELETED) {
            postRepository.findById(post.getId()).ifPresent(p -> p.setStatus(PostStatusType.DELETED));
            return;
        }
        if (post.getStatus() == PostStatusType.BANNED) {
            throw new RuntimeException("Only for admin");
        }
        if (post.getStatus() == PostStatusType.PUBLISHED && post.getNodes() == null) {
            throw new RuntimeException("Nodes are null");
        }
        if (post.getStatus() == PostStatusType.PENDING) {
            if (postRepository.findById(post.getId()).isEmpty()) {
                throw new RuntimeException();
            }
            Post currentPost = postRepository.findById(post.getId()).get();
            if (currentPost.getStatus() == PostStatusType.PUBLISHED) {
                currentPost.setStatus(PostStatusType.PENDING);
            } else {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            List<Node> nodes = nodeRepository.findAllByParentId(post.getId());
            post.setNodes(nodes);
        }
        return posts;
    }

    @Override
    public List<Post> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
        if (limit == null) limit = 10;
        if (offset == null) offset = 0;
        if (StringUtils.isEmpty(sort)) sort = "id";
        if (desc == null) desc = false;

        if (limit < 0 || offset < 0) {
            throw new ReckueIllegalArgumentException("Limit or offset is incorrect");
        }
        return findAllByTypeAndDesc(sort, desc).stream()
                .limit(limit)
                .skip(offset)
                .collect(Collectors.toList());
    }

    public List<Post> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<Post> posts = findAllBySortType(sort);
            Collections.reverse(posts);
            return posts;
        }
        return findAllBySortType(sort);
    }

    public List<Post> findAllBySortType(String sort) {
        switch (sort) {
            case "title":
                return findAllAndSortByTitle();
            case "source":
                return findAllAndSortBySource();
            case "createdDate":
                return findAllAndSortByCreatedDate();
            case "modificationDate":
                return findAllAndSortByModificationDate();
            case "status":
                return findAllAndSortByStatus();
            case "id":
                return findAllAndSortById();
            case "userId":
                return findAllAndSortByUserId();
            default:
                throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
        }
    }

    public List<Post> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getId))
                .collect(Collectors.toList());
    }

    public List<Post> findAllAndSortByTitle() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getTitle))
                .collect(Collectors.toList());
    }

    public List<Post> findAllAndSortByUserId() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getUserId))
                .collect(Collectors.toList());
    }

    public List<Post> findAllAndSortBySource() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getSource))
                .collect(Collectors.toList());
    }

    public List<Post> findAllAndSortByCreatedDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getCreatedDate))
                .collect(Collectors.toList());
    }

    public List<Post> findAllAndSortByModificationDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getModificationDate))
                .collect(Collectors.toList());
    }

    public List<Post> findAllAndSortByStatus() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getStatus))
                .collect(Collectors.toList());
    }

    @Override
    @NotNullArgs
    public Post findById(String id) {
        Optional<Post> post = postRepository.findById(id);
        List<Node> nodes = nodeRepository.findAllByParentId(id);
        if (post.isEmpty())
            throw new PostNotFoundException(id);

        post.ifPresent(p -> p.setNodes(nodes));
        return post.get();
    }

    @Override
    public List<Post> findAllByUserId(String userId, Integer limit, Integer offset) {
        if (limit == null) limit = 10;
        if (offset == null) offset = 0;
        if (limit < 0 || offset < 0) {
            throw new ReckueIllegalArgumentException("Limit or offset is incorrect");
        }
        return postRepository.findAllByUserId(userId)
                .stream()
                .limit(limit)
                .skip(offset)
                .collect(Collectors.toList());
    }

    @Override
    @NotNullArgs
    public void deleteById(String id) {
        if (!postRepository.existsById(id)) {
            throw new PostNotFoundException(id);
        }
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            String postUser = post.get().getUserId();
            if (CurrentUser.getId().equals(postUser) || CurrentUser.getRoles().contains(Role.ADMIN)) {
                postRepository.deleteById(id);
            } else {
                throw new ReckueAccessDeniedException("The operation is forbidden");
            }
        }
    }

    @Override
    @NotNullArgs
    public List<Post> findAllByTitle(String title) {
        return postRepository.findAllByTitle(title);
    }

}
