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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextNodeRequest.class, name = "TEXT"),
        @JsonSubTypes.Type(value = ImageNodeRequest.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = VideoNodeRequest.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = CodeNodeRequest.class, name = "CODE"),
        @JsonSubTypes.Type(value = ListNodeRequest.class, name = "LIST"),
        @JsonSubTypes.Type(value = AudioNodeRequest.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = PollNodeRequest.class, name = "POLL")
})
public class NodeParentRequest {
}
