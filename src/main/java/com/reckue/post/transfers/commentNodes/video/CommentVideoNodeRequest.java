package com.reckue.post.transfers.commentNodes.video;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.CommentNodeType;
import com.reckue.post.transfers.commentNodes.CommentNodeParentRequest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class CommentVideoNodeRequest represents an incoming DTO
 * for adding type of comment node such as a video.
 *
 * @author Artur Magomedov
 */
@Builder
@Data
public class CommentVideoNodeRequest implements CommentNodeParentRequest {

    @NotNull
    private String videoUrl;

    @NotNull
    private CommentNodeType type;

    @JsonCreator
    public CommentVideoNodeRequest(@NotNull String videoUrl,
                            @NotNull CommentNodeType type) {
        this.videoUrl = videoUrl;
        this.type = type;
    }
}
