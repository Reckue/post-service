package com.reckue.post.transfers.commentNodes.code;

import com.reckue.post.models.types.LangType;
import com.reckue.post.transfers.commentNodes.CommentNodeParentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class CommentCodeNodeResponse represents an outgoing DTO
 * for receiving type of comment node such as a language of content.
 *
 * @author Artur Magomedov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCodeNodeResponse implements CommentNodeParentResponse {

    private LangType language;
    private String content;
}
