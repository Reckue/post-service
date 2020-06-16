package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

/**
 * Class TextNodeResponse represents an outgoing DTO for receiving type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class TextNodeResponse implements NodeContent {

    private String content;
}
