package com.reckue.post.transfers.commentNodes.video;

import com.reckue.post.transfers.commentNodes.CommentNodeParentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class CommentVideoNodeResponse represents an outgoing DTO
 * for receiving type of comment node such as a video.
 *
 * @author Artur Magomedov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentVideoNodeResponse implements CommentNodeParentResponse {

    private String videoUrl;
}
