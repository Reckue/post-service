package com.reckue.post.transfers;

import com.reckue.post.utils.NodeContent;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class TextNodeRequest represents an incoming DTO for adding type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class TextNodeRequest implements NodeContent {

    @NotNull
    private String content;
}
