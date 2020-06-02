package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.VideoNode;
import com.reckue.post.repositories.VideoNodeRepository;
import com.reckue.post.services.VideoNodeService;
import com.reckue.post.utils.Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Class VideoServiceRealization represents realization of VideoNodeService.
 *
 * @author Viktor Grigoriev
 */
@Service
@RequiredArgsConstructor
public class VideoNodeServiceRealization implements VideoNodeService {

    private final VideoNodeRepository videoNodeRepository;

    /**
     * This method is used to create an object of class VideoNode.
     * Throws {@link ModelNotFoundException} in case if such object not found exists.
     *
     * @param videoNode object of class VideoNode
     * @return videoNode object of class VideoNode
     */
    @Override
    public VideoNode create(VideoNode videoNode) {
        if (!videoNodeRepository.existsById(videoNode.getId())) {
            videoNode.setId(Generator.id());
            return videoNodeRepository.save(videoNode);
        } else {
            throw new ModelAlreadyExistsException("Video node already exists");
        }
    }

    /**
     * This method is used to update data in an object of class VideoNode.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link IllegalArgumentException} in case
     * if such parameter is null.
     *
     * @param videoNode object of class VideoNode
     * @return videoNode object of class VideoNode
     */
    @Override
    public VideoNode update(VideoNode videoNode) {
        if (videoNode.getId() == null) {
            throw new IllegalArgumentException("The parameter is null");
        }
        VideoNode savedVideoNode = videoNodeRepository.findById(videoNode.getId()).orElseThrow(
                () -> new ModelNotFoundException("Video node by id " + videoNode.getId() + " is not found")
        );
        savedVideoNode.setVideoUrl(videoNode.getVideoUrl());
        return videoNodeRepository.save(savedVideoNode);
    }

    /**
     * This method is used to get all objects of class VideoNode.
     *
     * @return list of objects of class VideoNode
     */
    public List<VideoNode> findAll() {
        return videoNodeRepository.findAll();
    }

    /**
     * This method is used to get all objects of class VideoNode by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class VideoNode
     */
    @Override
    public List<VideoNode> findAll(int limit, int offset, String sort, boolean desc) {
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
     * @return list of objects of class VideoNode sorted by the selected parameter for sorting
     * in descending order
     */
    public List<VideoNode> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<VideoNode> nodes = findAllBySortType(sort);
            Collections.reverse(nodes);
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: videoUrl, default - id
     * @return list of objects of class VideoNode sorted by the selected parameter for sorting
     */
    public List<VideoNode> findAllBySortType(String sort) {
        if (sort.equals("videoUrl")) {
            return findAllAndSortByVideoUrl();
        }
        return findAllAndSortById();
    }

    /**
     * This method is used to sort objects by id.
     *
     * @return list of objects of class VideoNode sorted by id
     */
    public List<VideoNode> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(VideoNode::getId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by videoUrl.
     *
     * @return list of objects of class VideoNode sorted by videoUrl
     */
    public List<VideoNode> findAllAndSortByVideoUrl() {
        return findAll().stream()
                .sorted(Comparator.comparing(VideoNode::getVideoUrl))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return object of class VideoNode
     */
    @Override
    public VideoNode findById(String id) {
        return videoNodeRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException("Video node by id " + id + " is not found"));
    }

    /**
     * This method is used to delete an object by id.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     *
     * @param id object
     */
    @Override
    public void deleteById(String id) {
        if (videoNodeRepository.existsById(id)) {
            videoNodeRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("Video node by id " + id + " is not found");
        }
    }
}
