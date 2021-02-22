package com.reckue.post.controller;

import com.reckue.post.generated.controller.NodesApi;
import com.reckue.post.generated.controller.dto.NodeRequestDto;
import com.reckue.post.generated.controller.dto.NodeResponseDto;
import com.reckue.post.model.Node;
import com.reckue.post.service.NodeService;
import com.reckue.post.util.converter.NodeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NodeController implements NodesApi {

    private final NodeService nodeService;

    // @PreAuthorize("hasRole('USER')")
    @Override
    public ResponseEntity<NodeResponseDto> createNode(NodeRequestDto nodeRequestDto) {
        Node node = NodeConverter.convertToModel(nodeRequestDto);
        NodeResponseDto storedNodeResponseDto = NodeConverter.convertToDto(nodeService.create(node));
        return ResponseEntity.ok(storedNodeResponseDto);
    }

    @Override
    public ResponseEntity<NodeResponseDto> updateNode(NodeRequestDto nodeRequestDto) {
        Node node = NodeConverter.convertToModel(nodeRequestDto);
        NodeResponseDto updatedNodeResponseDto = NodeConverter.convertToDto(nodeService.update(node));
        return ResponseEntity.ok(updatedNodeResponseDto);
    }

    @Override
    public ResponseEntity<Void> deleteNodeById(String nodeId) {
        nodeService.deleteById(nodeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<NodeResponseDto> getNodeById(String nodeId) {
        return ResponseEntity.ok(NodeConverter.convertToDto(nodeService.findById(nodeId)));
    }

    @Override
    public ResponseEntity<List<NodeResponseDto>> getNodes(Integer limit, Integer offset, String sort, Boolean desc) {
        return ResponseEntity.ok(nodeService.findAll(limit, offset, sort, desc).getContent().stream()
                .map(NodeConverter::convertToDto)
                .collect(Collectors.toList()));
    }

}
