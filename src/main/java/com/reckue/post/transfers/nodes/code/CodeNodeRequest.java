package com.reckue.post.transfers.nodes.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.LangType;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Class CodeNodeRequest represents an incoming DTO for adding type of node such as a language of content.
 *
 * @author Kamila Meshcheryakova
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class CodeNodeRequest extends NodeParentRequest {

    @NotNull
    private LangType language;

    @NotNull
    private String content;

    @JsonCreator
    public CodeNodeRequest(@NotNull LangType language, @NotNull String content) {
        this.language = language;
        this.content = content;
    }
}
