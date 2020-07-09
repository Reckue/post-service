
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
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextNodeResponse.class, name = "text"),
        @JsonSubTypes.Type(value = ImageNodeResponse.class, name = "image"),
        @JsonSubTypes.Type(value = VideoNodeResponse.class, name = "video"),
        @JsonSubTypes.Type(value = CodeNodeResponse.class, name = "code"),
        @JsonSubTypes.Type(value = ListNodeResponse.class, name = "list"),
        @JsonSubTypes.Type(value = AudioNodeResponse.class, name = "audio"),
        @JsonSubTypes.Type(value = PollNodeResponse.class, name = "poll")
})
public class NodeParentResponse {
}