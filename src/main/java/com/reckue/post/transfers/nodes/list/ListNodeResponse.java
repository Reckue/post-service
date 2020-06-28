package com.reckue.post.transfers.nodes.list;

import com.reckue.post.utils.NodeWrapper;
import lombok.Data;

import java.util.List;

/**
 * Class ListNodeResponse represents an outgoing DTO for receiving type of node such as a list of contents.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class ListNodeResponse {

    private List<String> content;
}
