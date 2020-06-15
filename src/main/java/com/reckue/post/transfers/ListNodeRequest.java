package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

import java.util.List;

/**
 * Class ListNodeRequest represents an incoming DTO for adding type of node such as a list of contents.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class ListNodeRequest implements NodeContent {

    private List<String> content;
}
