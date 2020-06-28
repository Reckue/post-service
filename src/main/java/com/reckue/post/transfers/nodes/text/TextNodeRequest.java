package com.reckue.post.transfers.nodes.text;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class TextNodeRequest represents an incoming DTO for adding type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class TextNodeRequest {

    @NotNull
    private String content;
}
