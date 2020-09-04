package com.reckue.post.transfers.commentNodes.list;

import com.reckue.post.transfers.commentNodes.CommentNodeParentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class CommentListNodeResponse represents an outgoing DTO
 * for receiving type of comment node such as a list of contents.
 *
 * @author Artur Magomedov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentListNodeResponse implements CommentNodeParentResponse {

    private List<String> content;
}
