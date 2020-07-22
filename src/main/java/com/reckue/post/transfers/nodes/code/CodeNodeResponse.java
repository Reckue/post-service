package com.reckue.post.transfers.nodes.code;

import com.reckue.post.models.types.LangType;
import com.reckue.post.transfers.nodes.NodeParentResponse;
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
