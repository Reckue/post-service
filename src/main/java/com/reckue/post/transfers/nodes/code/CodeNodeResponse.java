package com.reckue.post.transfers.nodes.code;

import com.reckue.post.models.types.LangType;
import lombok.Data;

/**
 * Class CodeNodeResponse represents an outgoing DTO for receiving type of node such as a language of content.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class CodeNodeResponse {

    private LangType language;
    private String content;
}
