package com.reckue.post.transfers.nodes.list;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Class ListNodeRequest represents an incoming DTO for adding type of node such as a list of contents.
 *
 * @author Kamila Meshcheryakova
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class ListNodeRequest extends NodeParentRequest {

    private List<String> content;

    @JsonCreator
    public ListNodeRequest(List<String> content) {
        this.content = content;
    }
}
