package com.reckue.post.transfers.nodes.text;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Class TextNodeRequest represents an incoming DTO for adding type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class TextNodeRequest extends NodeParentRequest {

    @NotNull
    private String content;

    @NotNull
    private NodeType type;

    @JsonCreator
    public TextNodeRequest(@NotNull String content, @NotNull NodeType type) {
        this.content = content;
        this.type = type;
    }
}
