package com.reckue.post.transfers.nodes.text;

import com.reckue.post.transfers.nodes.NodeParentResponse;
import com.reckue.post.utils.NodeWrapper;
import lombok.Data;

/**
 * Class TextNodeResponse represents an outgoing DTO for receiving type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class TextNodeResponse extends NodeParentResponse {

    private String content;
}
