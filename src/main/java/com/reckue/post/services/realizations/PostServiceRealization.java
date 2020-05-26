package com.reckue.post.services.realizations;

import com.google.common.collect.Lists;
import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Post;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
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

    /**
     * This method is used to create an object of class Post.
     * Throws {@link ModelAlreadyExistsException} in case if such object already exists.
     *
     * @param post object of class Post
     * @return post object of class Post
     */
    @Override
    public Post create(Post post) {
        if (!postRepository.existsById(post.getId())) {
            post.setId(UUID.randomUUID().toString());
            return postRepository.save(post);
        } else {
            throw new ModelAlreadyExistsException("Post already exists.");
        }
    }

    /**
     * This method is used to update data in an object of class Post.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     *
     * @param post object of class Post
     * @return post object of class Post
     */
    @Override
    public Post update(Post post) {
        Post savedPost;
        if (post.getId() != null) {
            savedPost = postRepository.findById(post.getId()).orElseThrow(() ->
                    new ModelNotFoundException("Post not found by id " + post.getId() + "."));
            savedPost.setTitle(post.getTitle());
            savedPost.setNodes(post.getNodes());
            savedPost.setSource(post.getSource());
            savedPost.setTags(post.getTags());
            savedPost.setPublished(post.getPublished());
            savedPost.setChanged(post.getChanged());
            savedPost.setStatus(post.getStatus());
            return postRepository.save(savedPost);
        } else {
            throw new IllegalArgumentException("The parameter is null.");
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
    public List<Post> findAll(int limit, int offset, String sort, boolean desc) {
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
            return Lists.reverse(findAllBySortType(sort));
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: title, source, published, changed, status, default - id
     * @return list of objects of class Post sorted by the selected parameter for sorting
     */
    public List<Post> findAllBySortType(String sort) {
        switch (sort) {
            case "title":
                return findAllAndSortByTitle();
            case "source":
                return findAllAndSortBySource();
            case "published":
                return findAllAndSortByPublished();
            case "changed":
                return findAllAndSortByChanged();
            case "status":
                return findAllAndSortByStatus();
        }
        return findAllAndSortById();
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
     * This method is used to sort objects by publication date.
     *
     * @return list of objects of class Post sorted by publication date
     */
    public List<Post> findAllAndSortByPublished() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getPublished))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by date modified.
     *
     * @return list of objects of class Post sorted by date modified
     */
    public List<Post> findAllAndSortByChanged() {
        return findAll().stream()
                .sorted(Comparator.comparing(Post::getChanged))
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
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return object of class Post
     */
    @Override
    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException("Post not found by id " + id + "."));
    }

    /**
     * This method is used to delete an object by id.
     *
     * @param id object
     */
    @Override
    public void deleteById(String id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("Post not found by id " + id + ".");
        }
    }
}
