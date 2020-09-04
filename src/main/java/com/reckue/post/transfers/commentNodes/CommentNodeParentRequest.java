package com.reckue.post.transfers.commentNodes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.reckue.post.models.types.CommentNodeType;
import com.reckue.post.transfers.commentNodes.audio.CommentAudioNodeRequest;
import com.reckue.post.transfers.commentNodes.code.CommentCodeNodeRequest;
import com.reckue.post.transfers.commentNodes.image.CommentImageNodeRequest;
import com.reckue.post.transfers.commentNodes.list.CommentListNodeRequest;
import com.reckue.post.transfers.commentNodes.text.CommentTextNodeRequest;
import com.reckue.post.transfers.commentNodes.video.CommentVideoNodeRequest;

/**
 * Class CommentNodeParentRequest represents an incoming DTO for adding a common type of comment node.
 *
 * @author Artur Magomedov
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CommentTextNodeRequest.class, name = "TEXT"),
        @JsonSubTypes.Type(value = CommentImageNodeRequest.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = CommentVideoNodeRequest.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = CommentCodeNodeRequest.class, name = "CODE"),
        @JsonSubTypes.Type(value = CommentListNodeRequest.class, name = "LIST"),
        @JsonSubTypes.Type(value = CommentAudioNodeRequest.class, name = "AUDIO"),
})
public interface CommentNodeParentRequest {

    CommentNodeType getType();
}
