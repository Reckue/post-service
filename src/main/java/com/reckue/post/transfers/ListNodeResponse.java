package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

import java.util.List;

/**
 * Class ListNodeResponse represents an outgoing DTO for receiving type of node such as a list of contents.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class ListNodeResponse implements NodeContent {

    private List<String> content;
}
