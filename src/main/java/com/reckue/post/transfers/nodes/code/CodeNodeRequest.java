package com.reckue.post.transfers.nodes.code;

import com.reckue.post.models.types.LangType;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class CodeNodeRequest represents an incoming DTO for adding type of node such as a language of content.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class CodeNodeRequest {

    @NotNull
    private LangType language;

    @NotNull
    private String content;
}
