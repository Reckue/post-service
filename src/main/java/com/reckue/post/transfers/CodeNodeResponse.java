package com.reckue.post.transfers;

import com.reckue.post.models.LangType;
import com.reckue.post.utils.NodeContent;
import lombok.Data;

/**
 * Class CodeNodeResponse represents an outgoing DTO for receiving type of node such as a language of content.
 *
 * @author Kamila Meshcheryakova
 */
@Data
public class CodeNodeResponse implements NodeContent {

    private LangType language;
    private String content;
}
