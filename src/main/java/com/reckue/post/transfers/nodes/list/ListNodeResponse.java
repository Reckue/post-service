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
@EqualsAndHashCode(callSuper = false)
@Builder
public class ListNodeResponse implements NodeParentResponse {

    private List<String> content;
}
