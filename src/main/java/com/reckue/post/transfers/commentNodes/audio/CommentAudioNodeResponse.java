package com.reckue.post.transfers.commentNodes.audio;

import com.reckue.post.transfers.commentNodes.CommentNodeParentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class CommentAudioNodeResponse represents an outgoing DTO for receiving type of comment node such as an audio.
 *
 * @author Artur Magomedov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentAudioNodeResponse implements CommentNodeParentResponse {

    private String audioUrl;
}
