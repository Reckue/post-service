package com.reckue.post.transfers.nodes.text;

import com.reckue.post.transfers.nodes.NodeParentResponse;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class TextNodeResponse represents an outgoing DTO for receiving type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@Builder
public class TextNodeResponse extends NodeParentResponse {

    private String content;
}
