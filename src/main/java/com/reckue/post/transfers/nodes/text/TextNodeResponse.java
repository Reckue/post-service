package com.reckue.post.transfers.nodes.text;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.transfers.nodes.NodeParentResponse;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Class TextNodeResponse represents an outgoing DTO for receiving type of node such as a text.
 *
 * @author Kamila Meshcheryakova
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Builder
public class TextNodeResponse extends NodeParentResponse {

    private String content;

    private NodeType type = NodeType.TEXT;

    @JsonCreator
    public TextNodeResponse(@NotNull String content, @NotNull NodeType type) {
        this.content = content;
        this.type = type;
    }
}
