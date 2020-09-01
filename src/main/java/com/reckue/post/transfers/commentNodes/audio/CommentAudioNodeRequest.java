package com.reckue.post.transfers.commentNodes.audio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.CommentNodeType;
import com.reckue.post.transfers.commentNodes.CommentNodeParentRequest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class CommentAudioNodeRequest represents an incoming DTO for adding type of comment node such as an audio.
 *
 * @author Artur Magomedov
 */
@Builder
@Data
public class CommentAudioNodeRequest implements CommentNodeParentRequest {

    @NotNull
    private String audioUrl;

    @NotNull
    private CommentNodeType type;

    @JsonCreator
    public CommentAudioNodeRequest(@NotNull String audioUrl,
                                   @NotNull CommentNodeType type) {
        this.audioUrl = audioUrl;
        this.type = type;
    }
}
