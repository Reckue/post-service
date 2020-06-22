package com.reckue.post.transfers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.reckue.post.utils.NodeContent;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Class PollNodeRequest represents an incoming DTO for adding type of node such as an poll.
 *
 * @author Viktor Grigoriev
 */
@Data
@Builder
@JsonTypeName("poll")
public class PollNodeRequest implements NodeContent {

    @NotEmpty
    private List<@Size(min = 1, max = 128) String> items;

    @JsonCreator
    public PollNodeRequest(List<String> items) {
        this.items = items;
    }
}
