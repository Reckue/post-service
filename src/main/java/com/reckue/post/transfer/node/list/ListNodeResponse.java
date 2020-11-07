package com.reckue.post.transfer.node.list;

import com.reckue.post.transfer.node.NodeParentResponse;
import lombok.*;

import java.util.List;

/**
 * Class ListNodeResponse represents an outgoing DTO for receiving type of node such as a list of contents.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListNodeResponse implements NodeParentResponse {

    private List<String> content;
}
