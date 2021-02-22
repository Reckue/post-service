package com.reckue.post.transfer.node.text;

import com.reckue.post.transfer.node.NodeParentResponse;
import lombok.*;

/**
 * Class TextNodeResponse represents an outgoing DTO for receiving type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextNodeResponse implements NodeParentResponse {

    private String value;
}
