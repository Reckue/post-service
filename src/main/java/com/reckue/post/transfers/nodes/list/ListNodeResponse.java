package com.reckue.post.transfers.nodes.list;

import com.reckue.post.transfers.nodes.NodeParentResponse;
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
public class ListNodeResponse extends NodeParentResponse {

    private List<String> content;
}
