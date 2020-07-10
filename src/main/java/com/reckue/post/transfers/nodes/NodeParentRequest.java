package com.reckue.post.transfers.nodes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.reckue.post.transfers.nodes.audio.AudioNodeRequest;
import com.reckue.post.transfers.nodes.code.CodeNodeRequest;
import com.reckue.post.transfers.nodes.image.ImageNodeRequest;
import com.reckue.post.transfers.nodes.list.ListNodeRequest;
import com.reckue.post.transfers.nodes.poll.PollNodeRequest;
import com.reckue.post.transfers.nodes.text.TextNodeRequest;
import com.reckue.post.transfers.nodes.video.VideoNodeRequest;

/**
 * Class NodeParentRequest represents an incoming DTO for adding a common type of node.
 *
 * @author Kamila Meshcheryakova
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextNodeRequest.class, name = "text"),
        @JsonSubTypes.Type(value = ImageNodeRequest.class, name = "image"),
        @JsonSubTypes.Type(value = VideoNodeRequest.class, name = "video"),
        @JsonSubTypes.Type(value = CodeNodeRequest.class, name = "code"),
        @JsonSubTypes.Type(value = ListNodeRequest.class, name = "list"),
        @JsonSubTypes.Type(value = AudioNodeRequest.class, name = "audio"),
        @JsonSubTypes.Type(value = PollNodeRequest.class, name = "poll")
})
public class NodeParentRequest {
}