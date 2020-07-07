package com.reckue.post.transfers.nodes.text;

import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Class TextNodeRequest represents an incoming DTO for adding type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class TextNodeRequest extends NodeParentRequest {

    @NotNull
    private String content;
}
