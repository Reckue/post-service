package com.reckue.post.transfers.nodes.list;

import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Class ListNodeRequest represents an incoming DTO for adding type of node such as a list of contents.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class ListNodeRequest extends NodeParentRequest {

    private List<String> content;
}
