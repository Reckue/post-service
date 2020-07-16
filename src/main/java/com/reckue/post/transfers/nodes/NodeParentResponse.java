
package com.reckue.post.transfers.nodes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.reckue.post.transfers.nodes.audio.AudioNodeResponse;
import com.reckue.post.transfers.nodes.code.CodeNodeResponse;
import com.reckue.post.transfers.nodes.image.ImageNodeResponse;
import com.reckue.post.transfers.nodes.list.ListNodeResponse;
import com.reckue.post.transfers.nodes.poll.PollNodeResponse;
import com.reckue.post.transfers.nodes.text.TextNodeResponse;
import com.reckue.post.transfers.nodes.video.VideoNodeResponse;

/**
 * Class NodeParentResponse represents an outgoing DTO for receiving a common type of node.
 *
 * @author Kamila Meshcheryakova
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextNodeResponse.class, name = "TEXT"),
        @JsonSubTypes.Type(value = ImageNodeResponse.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = VideoNodeResponse.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = CodeNodeResponse.class, name = "CODE"),
        @JsonSubTypes.Type(value = ListNodeResponse.class, name = "LIST"),
        @JsonSubTypes.Type(value = AudioNodeResponse.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = PollNodeResponse.class, name = "POLL")
})
public class NodeParentResponse {
}