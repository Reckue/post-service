package com.reckue.post.transfers.commentNodes.text;

import com.reckue.post.transfers.commentNodes.CommentNodeParentResponse;
import com.reckue.post.transfers.nodes.NodeParentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class CommentTextNodeResponse represents an outgoing DTO for receiving type of comment node such as a text.
 *
 * @author Artur Magomedov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentTextNodeResponse implements CommentNodeParentResponse {

    private String content;
}
