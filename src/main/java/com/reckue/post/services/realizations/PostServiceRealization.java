package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.exceptions.models.post.PostNotFoundException;
import com.reckue.post.models.Node;
import com.reckue.post.models.Post;
import com.reckue.post.models.types.ParentType;
import com.reckue.post.models.types.PostStatusType;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.services.NodeService;
import com.reckue.post.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    private final NodeService nodeService;

    /**
     * This method is used to create an object of class Post.
     *
     * @param post object of class Post
     * @return post object of class Post
     */
    @Override
    @Transactional
    public Post create(Post post) {
        if (post == null) {
            throw new RuntimeException("Post is null");
        }
        validateOnCreateStatus(post);
        
        List<Node> nodeList = new ArrayList<>();
        Post storedPost = new Post();

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
     *
     * @param post object of class Post
     * @return post object of class Post
     */
    @Override
    @Transactional
    public Post update(Post post) {
        if (post == null) {
            throw new RuntimeException("Post is null");
        }
        if (post.getId() == null) {
            throw new ReckueIllegalArgumentException("The parameter is null");
        }
        if (!post.getNodes().isEmpty()) {
            post.getNodes().forEach(nodeService::create);
        }
        validateOnUpdateStatus(post);
        Post savedPost = postRepository
                .findById(post.getId())
                .orElseThrow(() -> new PostNotFoundException(post.getId()));
        savedPost.setUserId(post.getUserId());
        savedPost.setTitle(post.getTitle());
        savedPost.setNodes(post.getNodes());
        savedPost.setSource(post.getSource());
        savedPost.setTags(post.getTags());

        return postRepository.save(savedPost);
    }

    private void validateOnUpdateStatus(Post post) {
        if (post.getStatus() == null) {
            return;
        }
        if (post.getStatus() == PostStatusType.DRAFT) {
            postRepository.findById(post.getId()).get().setStatus(PostStatusType.DRAFT);
            return;
        }
        if (post.getStatus() == PostStatusType.PUBLISHED) {
            postRepository.findById(post.getId()).get().setStatus(PostStatusType.PUBLISHED);
            return;
        }
        if (post.getStatus() == PostStatusType.DELETED) {
            postRepository.findById(post.getId()).get().setStatus(PostStatusType.DELETED);
            return;
        }
        if (post.getStatus() == PostStatusType.BANNED) {
            throw new RuntimeException("Only for admin");
        }
        if (post.getStatus() == PostStatusType.PUBLISHED && post.getNodes() == null) {
            throw new RuntimeException("Nodes are null");
        }
        if (post.getStatus() == PostStatusType.PENDING) {
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
        return postRepository.findAll();
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
        return postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException(id));
    }

    /**
     * This method is used to delete an object by id.
     * Throws {@link PostNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     */
    @Override
    public void deleteById(String id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            throw new PostNotFoundException(id);
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
