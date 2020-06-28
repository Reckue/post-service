package com.reckue.post.transfers.nodes.list;

import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Class ListNodeResponse represents an outgoing DTO for receiving type of node such as a list of contents.
 *
 * @author Kamila Meshcheryakova
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListNodeResponse extends NodeParentRequest {

    private List<String> content;
}
