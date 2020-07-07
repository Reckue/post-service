package com.reckue.post.transfers.nodes.code;

import com.reckue.post.models.types.LangType;
import com.reckue.post.transfers.nodes.NodeParentResponse;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class CodeNodeResponse represents an outgoing DTO for receiving type of node such as a language of content.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@Builder
public class CodeNodeResponse extends NodeParentResponse {

    private LangType language;
    private String content;
}
