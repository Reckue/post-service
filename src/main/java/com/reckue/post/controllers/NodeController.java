package com.reckue.post.controllers;

import com.reckue.post.models.Node;
import com.reckue.post.services.NodeService;
import com.reckue.post.transfers.NodeRequest;
import com.reckue.post.transfers.NodeResponse;
import com.reckue.post.utils.converters.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class NodeController is responsible for processing incoming requests.
 *
 * @author Viktor Grigoriev
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "nodes")
public class NodeController {

    private final NodeService nodeService;

    /**
     * This type of request allows to create and process it using the converter.
     *
     * @param nodeRequest the object of class NodeRequest
     * @return the object of class NodeResponse
     */
    @PostMapping
    public NodeResponse create(@RequestBody NodeRequest nodeRequest) {
        return Converter.convert(nodeService.create(Converter.
                convert(nodeRequest, Node.class)), NodeResponse.class);
    }

    /**
     * This type of request allows to update by id the object and process it using the converter.
     *
     * @param id          the object identifier
     * @param nodeRequest the object of class NodeRequest
     * @return the object of class PostResponse
     */
    @PutMapping("/{id}")
    public NodeResponse update(@PathVariable String id, @RequestBody NodeRequest nodeRequest) {
        Node node = Converter.convert(nodeRequest, Node.class);
        node.setId(id);
        return Converter.convert(nodeService.update(node), NodeResponse.class);
    }

    /**
     * This type of request allows to find all the objects that meet the requirements, process their using the converter.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of given quantity of objects of class NodeResponse with a given offset
     * sorted by the selected parameter for sorting in descending order
     */
    @GetMapping
    public List<NodeResponse> findAll(@RequestParam int limit, @RequestParam int offset,
                                      @RequestParam String sort, @RequestParam boolean desc) {

        return nodeService.findAll(limit, offset, sort, desc).stream()
                .map(node -> Converter.convert(node, NodeResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * This type of request allows to get the object by id, process it using the converter.
     *
     * @param id the object identifier
     * @return the object of class NodeResponse
     */
    @GetMapping("/{id}")
    public NodeResponse findById(@PathVariable String id) {
        return Converter.convert(nodeService.findById(id), NodeResponse.class);
    }

    /**
     * This type of request allows to delete the object by id.
     *
     * @param id the object identifier
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        nodeService.deleteById(id);
    }
}
