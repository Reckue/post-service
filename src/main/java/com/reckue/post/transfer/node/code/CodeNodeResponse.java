package com.reckue.post.transfer.node.code;

import com.reckue.post.model.type.LangType;
import com.reckue.post.transfer.node.NodeParentResponse;
import lombok.*;

/**
 * Class CodeNodeResponse represents an outgoing DTO for receiving type of node such as a language of content.
 *
 * @author Kamila Meshcheryakova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeNodeResponse implements NodeParentResponse {

    private LangType language;
    private String content;
}
