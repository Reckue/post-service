package com.reckue.post.transfer.node.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.model.type.LangType;
import com.reckue.post.model.type.NodeType;
import com.reckue.post.transfer.node.NodeParentRequest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class CodeNodeRequest represents an incoming DTO for adding type of node such as a language of content.
 *
 * @author Kamila Meshcheryakova
 */
@Builder
@Data
public class CodeNodeRequest implements NodeParentRequest {

    @NotNull
    private LangType language;

    @NotNull
    private String content;

    @NotNull
    private NodeType type;

    @JsonCreator
    public CodeNodeRequest(@NotNull LangType language,
                           @NotNull String content,
                           @NotNull NodeType type) {
        this.language = language;
        this.content = content;
        this.type = type;
    }
}
