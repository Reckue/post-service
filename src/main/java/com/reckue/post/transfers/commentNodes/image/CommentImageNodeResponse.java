package com.reckue.post.transfers.commentNodes.image;

import com.reckue.post.transfers.commentNodes.CommentNodeParentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class CommentImageNodeResponse represents an outgoing DTO for receiving type of comment node such as an image.
 *
 * @author Artur Magomedov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentImageNodeResponse implements CommentNodeParentResponse {

    private String imageUrl;
}
