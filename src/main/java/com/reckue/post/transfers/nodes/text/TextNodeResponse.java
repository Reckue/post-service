package com.reckue.post.transfers.nodes.text;

import com.reckue.post.transfers.nodes.NodeParentResponse;
import lombok.*;

/**
 * Class TextNodeResponse represents an outgoing DTO for receiving type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class TextNodeResponse implements NodeParentResponse {

    private String content;
}
