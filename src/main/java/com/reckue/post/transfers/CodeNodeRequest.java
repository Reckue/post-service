package com.reckue.post.transfers;

import com.reckue.post.models.LangType;
import com.reckue.post.utils.NodeContent;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class CodeNodeRequest represents an incoming DTO for adding type of node such as a language of content.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class CodeNodeRequest implements NodeContent {

    @NotNull
    private LangType language;

    @NotNull
    private String content;
}
