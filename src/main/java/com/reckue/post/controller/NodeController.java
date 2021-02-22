package com.reckue.post.controller;

import com.reckue.post.generated.controller.NodesApi;
import com.reckue.post.generated.controller.dto.NodeRequestDto;
import com.reckue.post.generated.controller.dto.NodeResponseDto;
import com.reckue.post.model.Node;
import com.reckue.post.service.NodeService;
import com.reckue.post.util.converter.NodeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NodeController implements NodesApi {

    private final NodeService nodeService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/nodes")
    @Override
    public ResponseEntity<NodeResponseDto> createNode(NodeRequestDto nodeRequestDto) {
        Node node = NodeConverter.convertToModel(nodeRequestDto);
        return ResponseEntity.ok(NodeConverter.convertToDto(nodeService.create(node)));
    }

    //    @PostMapping
//    public NodeResponse create(@RequestBody @Valid NodeRequest nodeRequest) {
//        Node node = NodeConverter.convertToModel(nodeRequest);
//        Node storedNode = nodeService.create(node);
//        return NodeConverter.convertToDto(storedNode);
//    }
//
//    @PutMapping("/{id}")
//    public NodeResponse update(@PathVariable String id,
//                               @RequestBody @Valid NodeRequest nodeRequest) {
//        Node model = NodeConverter.convertToModel(nodeRequest);
//        model.setId(id);
//        return NodeConverter.convertToDto(nodeService.update(model));
//    }
//
//    @GetMapping
//    public List<NodeResponse> findAll(@RequestParam(required = false) Integer limit,
//                                      @RequestParam(required = false) Integer offset,
//                                      @RequestParam(required = false) String sort,
//                                      @RequestParam(required = false) Boolean desc) {
//        return nodeService.findAll(limit, offset, sort, desc).stream()
//                .map(NodeConverter::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/{id}")
//    public NodeResponse findById(@PathVariable String id) {
//        Node model = nodeService.findById(id);
//        return NodeConverter.convertToDto(model);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable String id) {
//        nodeService.deleteById(id);
//    }
}
