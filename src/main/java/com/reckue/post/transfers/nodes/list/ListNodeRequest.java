package com.reckue.post.transfers.nodes.list;

import com.reckue.post.utils.NodeWrapper;
import lombok.Data;

import java.util.List;

/**
 * Class ListNodeRequest represents an incoming DTO for adding type of node such as a list of contents.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class ListNodeRequest {

    private List<String> content;
}
