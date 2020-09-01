package com.reckue.post.controllers;

import com.reckue.post.controllers.apis.CommentNodeApi;
import com.reckue.post.models.CommentNode;
import com.reckue.post.services.CommentNodeService;
import com.reckue.post.transfers.CommentNodeRequest;
import com.reckue.post.transfers.CommentNodeResponse;
import com.reckue.post.utils.converters.CommentNodeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.reckue.post.utils.converters.CommentNodeConverter.convert;

/**
 * Class CommentNodeController is responsible for processing incoming requests.
 *
 * @author Artur Magomedov
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comment_nodes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentNodeController implements CommentNodeApi {

    private final CommentNodeService nodeService;

    /**
     * This type of request allows to create and process it using the converter.
     *
     * @param nodeRequest the object of class CommentNodeRequest
     * @return the object of class CommentNodeResponse
     */
    @PostMapping
    public CommentNodeResponse create(@RequestBody @Valid CommentNodeRequest nodeRequest) {
        log.info("{}", nodeRequest);
        return convert(nodeService.create(convert(nodeRequest)));
    }

    /**
     * This type of request allows to update by id the object and process it using the converter.
     *
     * @param id          the object identifier
     * @param nodeRequest the object of class CommentNodeRequest
     * @return the object of class CommentNodeResponse
     */
    @PutMapping("/{id}")
    public CommentNodeResponse update(@PathVariable String id, @RequestBody @Valid CommentNodeRequest nodeRequest) {
        CommentNode node = convert(nodeRequest);
        node.setId(id);
        return convert(nodeService.update(node));
    }

    /**
     * This type of request allows to find all the objects
     * that meet the requirements, process their using the converter.
     *
     * @param limit  quantity of objects
     * @param offset quantity to skip
     * @param sort   parameter for sorting
     * @param desc   sorting descending
     * @return list of given quantity of objects of class CommentNodeResponse with a given offset
     * sorted by the selected parameter for sorting in descending order
     */
    @GetMapping
    public List<CommentNodeResponse> findAll(@RequestParam(required = false) Integer limit,
                                      @RequestParam(required = false) Integer offset,
                                      @RequestParam(required = false) String sort,
                                      @RequestParam(required = false) Boolean desc) {

        return nodeService.findAll(limit, offset, sort, desc).stream()
                .map(CommentNodeConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * This type of request allows to get the object by id, process it using the converter.
     *
     * @param id the object identifier
     * @return the object of class CommentNodeResponse
     */
    @GetMapping("/{id}")
    public CommentNodeResponse findById(@PathVariable String id) {
        return convert(nodeService.findById(id));
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
