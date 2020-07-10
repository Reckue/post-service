package com.reckue.post.utils;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.reckue.post.transfers.nodes.image.ImageNodeRequest;
import com.reckue.post.transfers.nodes.poll.PollNodeRequest;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PollNodeRequest.class, name = "poll"),
        @JsonSubTypes.Type(value = ImageNodeRequest.class, name = "image")
})
public interface NodeContent {
}
