package com.reckue.post.transfers.nodes.code;

import com.reckue.post.models.types.LangType;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class CodeNodeResponse represents an outgoing DTO for receiving type of node such as a language of content.
 *
 * @author Kamila Meshcheryakova
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CodeNodeResponse extends NodeParentRequest {

    private LangType language;
    private String content;
}
