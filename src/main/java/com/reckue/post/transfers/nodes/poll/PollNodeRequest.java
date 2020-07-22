package com.reckue.post.transfers.nodes.poll;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.transfers.nodes.NodeParentRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Class PollNodeRequest represents an incoming DTO for adding type of node such as an poll.
 *
 * @author Viktor Grigoriev
 */
@Builder
@Data
public class PollNodeRequest implements NodeParentRequest {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 128)
    private String title;

    @NotNull
    @NotEmpty
    private List<@Size(min = 1, max = 128) String> items;

    @NotNull
    private NodeType type;

    @JsonCreator
    public PollNodeRequest(@NotNull @NotEmpty @Size(min = 1, max = 128) String title,
                           @NotNull @NotEmpty List<@Size(min = 1, max = 128) String> items,
                           @NotNull NodeType type) {
        this.title = title;
        this.items = items;
        this.type = type;
    }
}
