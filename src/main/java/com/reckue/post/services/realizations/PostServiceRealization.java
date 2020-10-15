package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ReckueAccessDeniedException;
import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.exceptions.models.post.PostNotFoundException;
import com.reckue.post.models.Node;
import com.reckue.post.models.Post;
import com.reckue.post.models.types.ParentType;
import com.reckue.post.models.types.PostStatusType;
import com.reckue.post.repositories.NodeRepository;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.services.NodeService;
import com.reckue.post.services.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class PostServiceRealization represents realization of PostService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class PostServiceRealization implements PostService {

    private final PostRepository postRepository;
    private final NodeRepository nodeRepository;
    private final NodeService nodeService;
    private final TokenStore tokenStore;

    /**
     * This method is used to create an object of class Post.
     *
     * @param post  object of class Post
     * @param token user token
     * @return post object of class Post
     */
    @Override
    @Transactional
    public Post create(Post post, String token) {
        if (post == null) {
            throw new RuntimeException("Post is null");
        }
        String userId = (String) tokenStore.readAccessToken(token)
                .getAdditionalInformation().get("userId");
        post.setUserId(userId);

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
                nodeService.create(node, token);
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

    /**
     * This method is used to update data in an object of class Post.
     * Throws {@link PostNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link ReckueIllegalArgumentException} in case
     * if parameter equals null.
     * Throws {@link ReckueAccessDeniedException} in case if the user isn't an post owner or
     * hasn't admin authorities.
     *
     * @param post  object of class Post
     * @param token user token
     * @return post object of class Post
     */
    @Override
    @Transactional
    public Post update(Post post, String token) {
        if (post == null) {
            throw new RuntimeException("Post is null");
        }
        if (post.getId() == null) {
            throw new ReckueIllegalArgumentException("The parameter is null");
        }

        if (!post.getNodes().isEmpty()) {
            post.getNodes().forEach(node -> {
                node.setParentId(post.getId());
                nodeService.create(node, token);
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

        Map<String, Object> tokenInfo = tokenStore.readAccessToken(token).getAdditionalInformation();
        if (!tokenInfo.get("userId").equals(savedPost.getUserId())
                && !tokenInfo.get("authorities").equals("ROLE_ADMIN")) {
            throw new ReckueAccessDeniedException("The operation is forbidden");
        }

        return postRepository.save(savedPost);
    }

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


    /**
     * This method is used to get all objects of class Post.
     *
     * @return list of objects of class Post
     */
    @Override
    public List<Post> findAll() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            List<Node> nodes = nodeRepository.findAllByParentId(post.getId());
            post.setNodes(nodes);
        }
        return posts;
    }

    /**
     * This method is used to get all objects of class Post by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of given quantity of objects of class Post with a given offset
     * sorted by the selected parameter for sorting in descending order
     */
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

    /**
     * This method is used to sort objects in descending order by type.
     *
     * @param sort parameter for sorting
     * @param desc sorting descending
     * @return list of objects of class Post sorted by the selected parameter for sorting
     * in descending order
     */
    public List<Post> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<Post> posts = findAllBySortType(sort);
            Collections.reverse(posts);
            return posts;
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: title, source, createdDate, modificationDate, status, default - id
     * @return list of objects of class Post sorted by the selected parameter for sorting
     */
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
        }
        throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
    }

    /**
     * This method is used to sort objects by id.
     *
     * @return list of objects of class Post sorted by id
     */
    public List<Post> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by type.
     *
     * @return list of objects of class Post sorted by type
     */
    public List<Post> findAllAndSortByTitle() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getTitle))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by userId.
     *
     * @return list of objects of class Post sorted by userId
     */
    public List<Post> findAllAndSortByUserId() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getUserId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by source.
     *
     * @return list of objects of class Post sorted by source
     */
    public List<Post> findAllAndSortBySource() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getSource))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by createdDate.
     *
     * @return list of objects of class Post sorted by createdDate
     */
    public List<Post> findAllAndSortByCreatedDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getCreatedDate))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by modificationDate.
     *
     * @return list of objects of class Post sorted by modificationDate
     */
    public List<Post> findAllAndSortByModificationDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getModificationDate))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by status.
     *
     * @return list of objects of class Post sorted by status
     */
    public List<Post> findAllAndSortByStatus() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getStatus))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link PostNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return post object of class Post
     */
    @Override
    public Post findById(String id) {
        Optional<Post> post = postRepository.findById(id);
        List<Node> nodes = nodeRepository.findAllByParentId(id);
        if (post.isEmpty())
            throw new PostNotFoundException(id);

        post.ifPresent(p -> p.setNodes(nodes));
        return post.get();
    }

    /**
     * This method is used to get a list of objects by user id.
     *
     * @param userId user identificator
     * @return list of objects of class Post
     */
    @Override
    // FIXME: correct the realization of this method
    public List<Post> findAllByUserId(String userId) {
        return postRepository.findAllByUserId(userId)
                .stream()
                .limit(10)
                .skip(0)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to delete an object by id.
     * Throws {@link PostNotFoundException} in case if such object isn't contained in database.
     * Throws {@link ReckueAccessDeniedException} in case if the user isn't an post owner or
     * hasn't admin authorities.
     *
     * @param id    object
     * @param token user token
     */
    @Override
    public void deleteById(String id, String token) {
        if (!postRepository.existsById(id)) {
            throw new PostNotFoundException(id);
        }
        Map<String, Object> tokenInfo = tokenStore.readAccessToken(token).getAdditionalInformation();
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            String postUser = post.get().getUserId();
            if (tokenInfo.get("userId").equals(postUser) || tokenInfo.get("authorities").equals("ROLE_ADMIN")) {
                postRepository.deleteById(id);
            } else {
                throw new ReckueAccessDeniedException("The operation is forbidden");
            }
        }
    }

    /**
     * This method is used to get the objects by title.
     *
     * @param title object
     * @return list of objects of class Post
     */
    @Override
    public List<Post> findAllByTitle(String title) {
        return postRepository.findAllByTitle(title);
    }

    /**
     * This method is used to delete all posts.
     */
    @Deprecated
    @Override
    public void deleteAll() {
        postRepository.deleteAll();
    }
}
