package com.reckue.post.transfer.node.text;

import com.reckue.post.model.type.NodeType;
import com.reckue.post.transfer.node.NodeParentRequest;
import lombok.Builder;
import lombok.Data;

/**
 * Class TextNodeRequest represents an incoming DTO for adding type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Builder
@Data
public class TextNodeRequest implements NodeParentRequest {

    private String value;

    private NodeType type;

}
