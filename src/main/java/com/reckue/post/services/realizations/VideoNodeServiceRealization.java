package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.VideoNode;
import com.reckue.post.repositories.VideoNodeRepository;
import com.reckue.post.services.VideoNodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VideoNodeServiceRealization implements VideoNodeService {

    @Autowired
    private VideoNodeRepository videoNodeRepository;

    @Override
    public VideoNode create (VideoNode textNode) {
        VideoNode savedTextNode;
        savedTextNode = textNode;
        savedTextNode.setId(UUID.randomUUID().toString());
        return videoNodeRepository.save(textNode);
    }

    @Override
    public VideoNode update(VideoNode textNode) {
        VideoNode savedVideoNode;
        if (textNode.getId() != null && videoNodeRepository.existsById(textNode.getId())) {
            savedVideoNode = videoNodeRepository.findById(textNode.getId()).get();
            savedVideoNode.setVideoUrl(textNode.getVideoUrl());
        } else {
            throw new ModelNotFoundException("VideoNodeNotFound by id");
        }
        return videoNodeRepository.save(savedVideoNode);
    }

    @Override
    public List<VideoNode> findAll(int limit, int offset, String sort, boolean desc) {
        return videoNodeRepository.findAll().stream()
                .limit(limit)
                .skip(offset)
                .sorted(Comparator.comparing(VideoNode::getId, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public VideoNode findById(String id) {
        return videoNodeRepository.findById(id).orElseThrow(
                ()-> new ModelNotFoundException("VideoNodeNotFound by id"));
    }

    @Override
    public void deleteById(String id) {
        if(videoNodeRepository.existsById(id)) {
            videoNodeRepository.deleteById(id);
        } else {
            throw new RuntimeException("VideoNodeNotFound by id");
        }
    }
}

