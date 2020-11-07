package com.reckue.post.transfer.node;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.reckue.post.model.type.NodeType;
import com.reckue.post.transfer.node.audio.AudioNodeRequest;
import com.reckue.post.transfer.node.code.CodeNodeRequest;
import com.reckue.post.transfer.node.image.ImageNodeRequest;
import com.reckue.post.transfer.node.list.ListNodeRequest;
import com.reckue.post.transfer.node.poll.PollNodeRequest;
import com.reckue.post.transfer.node.text.TextNodeRequest;
import com.reckue.post.transfer.node.video.VideoNodeRequest;

/**
 * Class NodeParentRequest represents an incoming DTO for adding a common type of node.
 *
 * @author Kamila Meshcheryakova
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextNodeRequest.class, name = "TEXT"),
        @JsonSubTypes.Type(value = ImageNodeRequest.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = VideoNodeRequest.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = CodeNodeRequest.class, name = "CODE"),
        @JsonSubTypes.Type(value = ListNodeRequest.class, name = "LIST"),
        @JsonSubTypes.Type(value = AudioNodeRequest.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = PollNodeRequest.class, name = "POLL")
})
public interface NodeParentRequest {

    NodeType getType();
}
