package com.reckue.post.transfers.nodes.poll;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Class PollNodeRequest represents an incoming DTO for adding type of node such as an poll.
 *
 * @author Viktor Grigoriev
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class PollNodeRequest extends NodeParentRequest {

    @NotEmpty
    @Size(min = 1, max = 128)
    private String title;

    @NotEmpty
    private List<@Size(min = 1, max = 128) String> items;

    @JsonCreator
    public PollNodeRequest(String title, List<String> items) {
        this.title = title;
        this.items = items;
    }
}
