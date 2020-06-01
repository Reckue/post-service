package com.reckue.post.services.realizations;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.ImageNode;
import com.reckue.post.repositories.ImageNodeRepository;
import com.reckue.post.services.ImageNodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Class ImageNodeServiceRealization represents realization of ImageNodeService.
 *
 * @author Daria Smirnova
 */
@Service
@RequiredArgsConstructor
public class ImageNodeServiceRealization implements ImageNodeService {

    private final ImageNodeRepository imageNodeRepository;

    /**
     * This method is used to create an object of class ImageNode.
     * Throws {@link ModelAlreadyExistsException} in case if such object already exists.
     *
     * @param imageNode object of class ImageNode
     * @return imageNode object of class ImageNode
     */
    @Override
    public ImageNode create(ImageNode imageNode) {
        if (!imageNodeRepository.existsById(imageNode.getId())) {
            imageNode.setId(UUID.randomUUID().toString());
            return imageNodeRepository.save(imageNode);
        } else {
            throw new ModelAlreadyExistsException("ImageNode already exists.");
        }
    }

    /**
     * This method is used to update data in an object of class ImageNode.
     * Throws {@link ModelNotFoundException} in case
     * if such object isn't contained in database.
     * Throws {@link IllegalArgumentException} in case
     * if such parameter is null.
     *
     * @param imageNode object of class ImageNode
     * @return imageNode object of class ImageNode
     */
    @Override
    public ImageNode update(ImageNode imageNode) {
        if (imageNode.getId() == null) {
            throw new IllegalArgumentException("The parameter is null.");
        }
        ImageNode savedImageNode = imageNodeRepository.findById(imageNode.getId()).orElseThrow(
                () -> new ModelNotFoundException("Image node by id " + imageNode.getId() + " is not found")
        );
        savedImageNode.setImageUrl(imageNode.getImageUrl());
        return imageNodeRepository.save(savedImageNode);
    }

    /**
     * This method is used to get all objects of class ImageNode.
     *
     * @return list of objects of class ImageNode
     */
    @Override
    public List<ImageNode> findAll() {
        return imageNodeRepository.findAll();
    }

    /**
     * This method is used to get all objects of class ImageNode by parameters.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of objects of class ImageNode
     */
    @Override
    public List<ImageNode> findAll(int limit, int offset, String sort, boolean desc) {
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
     * @return list of objects of class ImageNode sorted by the selected parameter for sorting
     * in descending order
     */
    public List<ImageNode> findAllByTypeAndDesc(String sort, boolean desc) {
        if (desc) {
            List<ImageNode> nodes = findAllBySortType(sort);
            Collections.reverse(nodes);
        }
        return findAllBySortType(sort);
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: imageUrl, default - id
     * @return list of objects of class ImageNode sorted by the selected parameter for sorting
     */
    public List<ImageNode> findAllBySortType(String sort) {
        if (sort.equals("imageUrl")) {
            return findAllAndSortByImageUrl();
        }
        return findAllAndSortById();
    }

    /**
     * This method is used to sort objects by id.
     *
     * @return list of objects of class ImageNode sorted by id
     */
    public List<ImageNode> findAllAndSortById() {
        return findAll().stream()
                .sorted(Comparator.comparing(ImageNode::getId))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to sort objects by imageUrl.
     *
     * @return list of objects of class ImageNode sorted by content
     */
    public List<ImageNode> findAllAndSortByImageUrl() {
        return findAll().stream()
                .sorted(Comparator.comparing(ImageNode::getImageUrl))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get an object by id.
     * Throws {@link ModelNotFoundException} in case if such object isn't contained in database.
     *
     * @param id object
     * @return imageNode object of class ImageNode
     */
    @Override
    public ImageNode findById(String id) {
        return imageNodeRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException("Image node by id " + id + " is not found"));
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
        if (imageNodeRepository.existsById(id)) {
            imageNodeRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("Image node by id " + id + " is not found");
        }
    }
}
